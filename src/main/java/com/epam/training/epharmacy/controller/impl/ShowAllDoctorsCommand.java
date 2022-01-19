package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import com.epam.training.epharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class ShowAllDoctorsCommand implements Command {

    private final Logger LOG = LogManager.getLogger(ShowAllDoctorsCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    UserService userService = factory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {

        try {
            req.setAttribute("doctors", userService.showDoctors());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/doctors.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during returning doctors list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
