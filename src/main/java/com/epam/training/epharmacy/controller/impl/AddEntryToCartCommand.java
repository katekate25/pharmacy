package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.*;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.*;

public class AddEntryToCartCommand implements Command {

    private final Logger LOG = LogManager.getLogger(AddEntryToCartCommand.class);
    OrderService orderService = ServiceFactory.getInstance().getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
           throw new PermissionsDeniedException();
        }

        String serialNumber = req.getParameter("serialNumber");
        Double amount = Double.valueOf(req.getParameter("packageAmount"));

        try {
            orderService.addOrUpdateOrderEntry(serialNumber, amount, currentUser);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_CART");

        } catch (ServiceException e){
            LOG.error("Error during adding order Entry", e);
            resp.sendRedirect(ERROR_PAGE);
        }

    }
}
