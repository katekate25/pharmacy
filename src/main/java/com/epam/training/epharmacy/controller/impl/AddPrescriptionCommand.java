package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Prescription;
import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddPrescriptionCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {
        ServiceFactory factory = ServiceFactory.getInstance();
        PrescriptionService prescriptionService = factory.getPrescriptionService();

        String serialNumber = req.getParameter("serialNumber");
        HttpSession session = req.getSession(false);
        String doctor = session.getAttribute("login").toString();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");


        Prescription prescription = new Prescription.Builder()
                .medicine(prescriptionService.getMedicineByNameSerialNumber(serialNumber))
                .packageAmount(Double.parseDouble(req.getParameter("packageAmount")))
                .client(prescriptionService.getUserByLogin(req.getParameter("client")))
                .doctor(prescriptionService.getUserByLogin(req.getParameter(doctor)))
                .creationDate(formatter.parse(req.getParameter("creationDate")))
                .expirationDate(formatter.parse(req.getParameter("expirationDate")))
                .build();

        try {
            prescriptionService.addPrescription(prescription);

        } catch (IllegalArgumentException e) {
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
            return;
        }
//        HttpSession session = req.getSession(true);
//        session.setAttribute("user", user);
        resp.sendRedirect("/pharmacy/controller?command=GO_TO_PRESCRIPTION_PAGE&prescriptionAdded=true");
    }
}
