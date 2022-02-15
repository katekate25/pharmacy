package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.Order;
import com.epam.training.epharmacy.entity.PrescriptionStatus;
import com.epam.training.epharmacy.entity.User;
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

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class AddPrescriptionToEntryCommand implements Command {
    private final Logger LOG = LogManager.getLogger(AddPrescriptionToEntryCommand.class);
    OrderService orderService = ServiceFactory.getInstance().getOrderService();
    PrescriptionService prescriptionService = ServiceFactory.getInstance().getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }
        String entryId = req.getParameter("entryNumber");
        String prescrNumber = req.getParameter("prescrNumber");

        try {
            orderService.addPrescriptionToEntry(Integer.valueOf(entryId), Integer.valueOf(prescrNumber));
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_CART");

        } catch (ServiceException | SQLException e){
            LOG.error("Error during adding prescription to order Entry", e);
            resp.sendRedirect(ERROR_PAGE);
        }

    }
}
