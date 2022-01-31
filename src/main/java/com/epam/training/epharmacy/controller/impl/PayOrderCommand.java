package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.Prescription;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.*;

public class PayOrderCommand implements Command {

    private final Logger LOG = LogManager.getLogger(PayOrderCommand.class);
    OrderService orderService = ServiceFactory.getInstance().getOrderService();
    PrescriptionService prescriptionService = ServiceFactory.getInstance().getPrescriptionService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = ControllerUtils.getUserFromRequest(req);
        if (user == null) {
            throw new PermissionsDeniedException();
        }

        try {
            String deliveryTime = req.getParameter("deliveryTime");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(deliveryTime);
            orderService.payOrder(user, date);
            resp.sendRedirect(ORDERS_PAGE);
        } catch (ServiceException | ParseException e){
            LOG.error("Error during payment", e);
            resp.sendRedirect(ERROR_PAGE);
        }

    }
}
