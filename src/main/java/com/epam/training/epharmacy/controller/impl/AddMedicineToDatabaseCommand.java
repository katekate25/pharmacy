package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.DiseaseGroup;
import com.epam.training.epharmacy.entity.Medicine;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class AddMedicineToDatabaseCommand implements Command {

    private final Logger LOG = LogManager.getLogger(AddMedicineToDatabaseCommand.class);
    MedicinesService medicinesService = ServiceFactory.getInstance().getMedicinesService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null || currentUser.getUserRole() != UserRole.PHARMACIST) {
            throw new PermissionsDeniedException();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Double arrival = Double.parseDouble(req.getParameter("productArrival"));

        try {
            Medicine medicine = new Medicine.Builder()
                    .commercialName(req.getParameter("commercialName"))
                    .internationalName(req.getParameter("internationalName"))
                    .medicineDose(Integer.parseInt(req.getParameter("medicineDose")))
                    .medicineForm(req.getParameter("medicineForm"))
                    .diseaseGroup(DiseaseGroup.valueOf(req.getParameter("diseaseGroup")))
                    .invoiceNumber(req.getParameter("invoiceNumber"))
                    .medicineExpirationDate(formatter.parse(req.getParameter("medicineExpirationDate")))
                    .isPrescriptionRequired(Boolean.parseBoolean(req.getParameter("prescriptionRequired")))
                    .packagePrice(Double.parseDouble(req.getParameter("packagePrice")))
                    .producer(medicinesService.getProducerByName(req.getParameter("producerName")))
                    .arrivalDate(formatter.parse(req.getParameter("arrivalDate")))
                    .productArrival(arrival)
                    .productBalance(arrival)
                    .serialNumber(req.getParameter("serialNumber"))
                    .build();
            medicinesService.addMedicine(medicine);

        } catch (Exception e) {
            LOG.error("Error during adding medicine", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
        }

        resp.sendRedirect("/pharmacy/controller?command=GO_TO_MEDICINE_FORM&successfulAdding=true");

    }
}
