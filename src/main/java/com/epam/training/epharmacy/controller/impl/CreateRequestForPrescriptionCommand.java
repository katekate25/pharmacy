package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.constant.ControllerConstants;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.Message;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.MessageService;
import com.epam.training.epharmacy.service.UserService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CreateRequestForPrescriptionCommand implements Command {

    private final Logger LOG = LogManager.getLogger(CreateRequestForPrescriptionCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final MessageService messageService = ServiceFactory.getInstance().getMessageService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }

        String recipient = req.getParameter("recipient");
        try {
            Message message = new Message();
            message.setMessage(req.getParameter("message"));
            message.setMessageDate(new Date());
            message.setRecipient(userService.getUserByLogin(recipient));
            message.setSender(currentUser);
            messageService.sendMessage(message);

        } catch (ServiceException e) {
            LOG.error("Error during sending message", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
        }
        resp.sendRedirect(ControllerConstants.DOCTOR_PAGE
                + String.format("&recipient=%s&sendSuccessful=true", recipient));
    }
}
