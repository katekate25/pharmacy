package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.OrderEntry;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEntryToCartCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        OrderEntryService orderEntryService = factory.getOrderEntryService();

        List<OrderEntry> orderEntries = new ArrayList<>();
        String serialNumber = req.getParameter("serialNumber");
        Double amount = Double.valueOf(req.getParameter("packageAmount"));
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("test");
        User currentUser = (User) req.getSession().getAttribute("user");

        try {
            orderEntries.add(orderEntryService.addOrderEntry( serialNumber, amount, currentUser));
            req.setAttribute("orderEntries", orderEntries );
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/medicine.jsp");
            dispatcher.forward(req, resp);

//            resp.sendRedirect("/pharmacy/controller?command=SHOW_MEDICINE_DETAILS&serialNumber=${medicine.serialNumber}");

        } catch (ServiceException e){
            //log
            //send to error page
        }

    }
}
