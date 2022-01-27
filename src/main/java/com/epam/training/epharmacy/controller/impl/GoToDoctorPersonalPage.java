package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class GoToDoctorPersonalPage implements Command {

    private final Logger LOG = LogManager.getLogger(GoToDoctorPersonalPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String login = req.getParameter("recipient");

        try {
            req.setAttribute("recipient", login );
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/doctorPersonalPage.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during redirecting to doctor personal page", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
