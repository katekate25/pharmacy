package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.Prescription;
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

public class GoToPrescriptionsListCommand implements Command {
    private final Logger LOG = LogManager.getLogger(GoToPersonalCabinet.class);
    PrescriptionService prescriptionService = ServiceFactory.getInstance().getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }

        try {
            String client = req.getParameter("client");
            List<Prescription> prescriptions = StringUtils.isNotBlank(client) ?
                    prescriptionService.getAllUserPrescription(client) :
                    prescriptionService.getAllPrescription();

            req.setAttribute("prescriptions", prescriptionService.getAllUserPrescription(currentUser.getLogin()));
            req.setAttribute("prescriptionsList", prescriptions);


            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/prescriptionsList.jsp");
            dispatcher.forward(req, resp);
        }catch (ServiceException e){
            LOG.error("Error during redirecting to prescriptions list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
