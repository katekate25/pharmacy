package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.constant.ControllerConstants;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.OrderStatus;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.MessageService;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateMessageApprovalStatusCommand implements Command {
    private final Logger LOG = LogManager.getLogger(UpdateMessageApprovalStatusCommand.class);
    private final MessageService messageService = ServiceFactory.getInstance().getMessageService();
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null || currentUser.getUserRole() != UserRole.DOCTOR) {
            throw new PermissionsDeniedException();
        }
        try {
            messageService.updateMessage(Integer.valueOf(req.getParameter("messageId")), Boolean.parseBoolean(req.getParameter("approvalStatus")));
            resp.sendRedirect(ControllerConstants.MESSAGE_LIST);
        } catch (ServiceException e){
            LOG.error("Error during updating message", e);
            resp.sendRedirect(ControllerConstants.ERROR_PAGE);
        }
    }
}
