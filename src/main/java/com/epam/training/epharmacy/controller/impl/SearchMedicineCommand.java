package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SearchMedicineCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException {
        String name = req.getParameter("commercialName");
        ServiceFactory factory = ServiceFactory.getInstance();
        MedicinesService medicinesService = factory.getMedicinesService();

        try {
            List<Medicine> medicines = medicinesService.findMedicine(name);
            req.setAttribute("medicines", medicines);
            if (medicines != null && !medicines.isEmpty())
            {
                req.setAttribute("analogueFind", medicinesService.findMedicineAnalogue(medicines.iterator().next().getInternationalName()));
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp");
            dispatcher.forward(req, resp);
//            resp.sendRedirect(req.getContextPath() + "/controller?command=SEARCH_MEDICINES");
        } catch (ServiceException e){
            //log
            //send to error page
        }
    }
}
