package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.service.MedicinesService;
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

public class ShowMedicineDetailsCommand implements Command {

    private final Logger LOG = LogManager.getLogger(ShowMedicineDetailsCommand.class);
    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final MedicinesService medicinesService = factory.getMedicinesService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String serialNumber = req.getParameter("serialNumber");
        try {
            req.setAttribute("medicineBySeries", medicinesService.findMedicineToShow(serialNumber));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/medicine.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during returning medicine page", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
