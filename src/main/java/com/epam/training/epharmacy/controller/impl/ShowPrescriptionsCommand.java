package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.PrescriptionService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class ShowPrescriptionsCommand implements Command {

    private final Logger LOG = LogManager.getLogger(ShowAllDoctorsCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    PrescriptionService prescriptionService = factory.getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }

        try {
           req.setAttribute("prescriptions", prescriptionService.getAllUserPrescription(currentUser.getLogin()));
            String client = req.getParameter("client");
            if (StringUtils.isNotBlank(client)) {
                req.setAttribute("foundPrescription", prescriptionService.getAllUserPrescription(client));
            }


            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/prescriptionsList.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during returning prescriptions list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
