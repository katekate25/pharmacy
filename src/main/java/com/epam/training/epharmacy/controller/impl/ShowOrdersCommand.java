package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Order;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowOrdersCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        OrderService orderService = factory.getOrderService();
        String id = req.getParameter("userid");

        try {
            List<Order> orders = orderService.showOrdersToAdmin();
            req.setAttribute("ordersAdmin", orders);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/personalCabinet.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            //log
            //send to error page
        }
    }
}
