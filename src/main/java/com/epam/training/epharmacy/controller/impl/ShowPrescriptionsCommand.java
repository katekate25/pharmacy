package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.PrescriptionService;
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
import java.sql.SQLException;
import java.text.ParseException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class ShowPrescriptionsCommand implements Command {

    private final Logger LOG = LogManager.getLogger(ShowAllDoctorsCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    PrescriptionService prescriptionService = factory.getPrescriptionService();
    UserService userService = factory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }

        try {
            req.setAttribute("prescriptions", prescriptionService.showPrescription(currentUser.getLogin()));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/personalPage.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during returning doctors list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
