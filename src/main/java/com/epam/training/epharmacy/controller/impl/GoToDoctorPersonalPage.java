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

public class GoToDoctorPersonalPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {

        String login = req.getParameter("recipient");

        try {
            req.setAttribute("recipient", login );
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/doctorPersonalPage.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            //log
            //send to error page
        }
    }
}
