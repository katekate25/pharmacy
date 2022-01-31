package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.commons.collections4.CollectionUtils;
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

public class GoToCatalogPage implements Command {

    private final Logger LOG = LogManager.getLogger(GoToCatalogPage.class);
    private final ServiceFactory factory = ServiceFactory.getInstance();
    private final MedicinesService medicinesService = factory.getMedicinesService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        try {
            String commercialName = req.getParameter("commercialName");
            List<Medicine> medicines = StringUtils.isNotBlank(commercialName) ?
                     medicinesService.findMedicine(commercialName, true) :
                     medicinesService.findAllMedicines();
            req.setAttribute("medicines", medicines);
            if (StringUtils.isNotBlank(commercialName) && CollectionUtils.isNotEmpty(medicines))
            {
                List<Medicine> analogs = medicinesService
                        .findMedicineAnalogue(medicines.iterator().next());
                req.setAttribute("analogs", analogs);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/catalog.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException e){
            LOG.error("Error during redirecting to catalog", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
