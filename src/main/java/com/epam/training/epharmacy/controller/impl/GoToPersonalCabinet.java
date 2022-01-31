package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.MessageService;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.UserService;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToPersonalCabinet implements Command {

    private final Logger LOG = LogManager.getLogger(GoToPersonalCabinet.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    MessageService messageService = factory.getMessageService();
    UserService userService = factory.getUserService();
    OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }

        req.setAttribute("messages", messageService.showMessagesToUser(currentUser) );

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/personalCabinet.jsp");
        dispatcher.forward(req, resp);
    }
}
