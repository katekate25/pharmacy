package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.OrderEntryService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ChooseMedicineAmountCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException {
        ServiceFactory factory = ServiceFactory.getInstance();
        MedicinesService medicinesService = factory.getMedicinesService();
        OrderEntryService orderEntryService = factory.getOrderEntryService();

        String login = req.getParameter("login");
        String amount = req.getParameter("password");

        try {
       //     req.setAttribute("medicineBySeries", medicinesService.findMedicineToShow(serialNumber));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/medicine.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            //log
            //send to error page
        }
       // orderEntryService.addOrderEntry(serialNumber, amount)
    }
}
