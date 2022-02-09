package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.OrderEntryService;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.PrescriptionService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class FindPrescriptionForEntryCommand implements Command {
    Logger logger = LogManager.getLogger(FindPrescriptionForEntryCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    PrescriptionService prescriptionService = factory.getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }
        Date currentDate = new Date();

        try {
            req.setAttribute("validPrescriptions", prescriptionService.findValidPrescriptionsByMedicine(currentUser, currentDate, req.getParameter("serialNumber")));
        }catch (ServiceException e) {
            logger.error("Problems on finding prescription", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
        }

    }
}
