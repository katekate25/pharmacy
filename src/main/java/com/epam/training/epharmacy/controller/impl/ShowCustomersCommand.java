package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.service.UserService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class ShowCustomersCommand implements Command {

    private final Logger LOG = LogManager.getLogger(ShowCustomersCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    UserService userService = factory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            req.setAttribute("customers", userService.showCustomers());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/personalPage.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during returning customers list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
