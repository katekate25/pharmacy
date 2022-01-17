package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class GoToPrescriptionFormPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {

        String serialNumber = req.getParameter("serialNumber");

        try {
            req.setAttribute("serialNumber", serialNumber );
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/prescription.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            //log
            //send to error page
        }
    }
}
