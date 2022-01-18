package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddEntryToCartCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();

        String serialNumber = req.getParameter("serialNumber");
        Double amount = Double.valueOf(req.getParameter("packageAmount"));
        User currentUser = (User) req.getSession().getAttribute("user");

        try {
            orderService.addOrUpdateOrderEntry(serialNumber, amount, currentUser);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_CART");

        } catch (ServiceException e){
            //log
            //send to error page
        }

    }
}
