package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.PrescriptionService;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class GoToCartCommand implements Command {

    OrderService orderService = ServiceFactory.getInstance().getOrderService();
    PrescriptionService prescriptionService = ServiceFactory.getInstance().getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = ControllerUtils.getUserFromRequest(req);
        if (user == null) {
            throw new PermissionsDeniedException();
        }

        req.setAttribute("prescriptions", prescriptionService.showPrescription(user.getLogin()));
        req.setAttribute("order", orderService.getCartForUser(user));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/cart.jsp");
        dispatcher.forward(req, resp);
    }
}
