package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.constant.ControllerConstants;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.OrderStatus;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateOrderStatusCommand implements Command {
    private final Logger LOG = LogManager.getLogger(UpdateOrderStatusCommand.class);
    private final OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null || currentUser.getUserRole() != UserRole.PHARMACIST) {
            throw new PermissionsDeniedException();
        }
        try {
            orderService.updateOrder(Integer.valueOf(req.getParameter("orderNumber")), OrderStatus.valueOf(req.getParameter("orderStatus")));;
            resp.sendRedirect(ControllerConstants.ORDER_LIST);
        } catch (ServiceException | SQLException e){
            LOG.error("Error during updating order", e);
            resp.sendRedirect(ControllerConstants.ERROR_PAGE);
        }
    }
}
