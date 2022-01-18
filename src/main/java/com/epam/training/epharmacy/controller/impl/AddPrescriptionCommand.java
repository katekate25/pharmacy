package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Prescription;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.*;

public class AddPrescriptionCommand implements Command {

    private final Logger LOG = LogManager.getLogger(AddPrescriptionCommand.class);
    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final PrescriptionService prescriptionService = factory.getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {

        HttpSession session = req.getSession(false);
        User doctor = (User) session.getAttribute("user");
        if (doctor == null || doctor.getUserRole() != UserRole.DOCTOR)
        {
            resp.sendRedirect(PHARMACY_CONTROLLER + "command=GO_TO_MAIN_PAGE&accessDenied=true");
        }

        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT_PATTERN);

        try {
            Prescription prescription = new Prescription.Builder()
                    .medicine(prescriptionService.getMedicineByNameSerialNumber(req.getParameter("serialNumber")))
                    .packageAmount(Double.parseDouble(req.getParameter("packageAmount")))
                    .client(prescriptionService.getUserByLogin(req.getParameter("client")))
                    .usageInstruction(req.getParameter("usageInstruction"))
                    .doctor(prescriptionService.getUserByLogin(doctor.getLogin()))
                    .creationDate(formatter.parse(req.getParameter("creationDate")))
                    .expirationDate(formatter.parse(req.getParameter("expirationDate")))
                    .build();
            prescriptionService.addPrescription(prescription);

        } catch (ServiceException e) {
            LOG.error("Something went wrong", e);
            resp.sendRedirect(ERROR_PAGE);
            return;
        }
        resp.sendRedirect(PHARMACY_CONTROLLER + "command=GO_TO_PRESCRIPTION_PAGE&prescriptionAdded=true");
    }
}
