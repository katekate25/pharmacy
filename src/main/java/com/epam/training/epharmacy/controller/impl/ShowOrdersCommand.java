package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class ShowOrdersCommand implements Command {

    private final Logger LOG = LogManager.getLogger(ShowOrdersCommand.class);
    OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null)
        {
            throw new PermissionsDeniedException();
        }

        try {
            req.setAttribute("orders", orderService.getOrdersForUser(currentUser));

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/orders.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during returning orders list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
