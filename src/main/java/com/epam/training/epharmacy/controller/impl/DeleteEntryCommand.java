package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.OrderService;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;
import static com.epam.training.epharmacy.controller.constant.ControllerConstants.GREETING_PAGE;

public class DeleteEntryCommand implements Command {

    private final Logger LOG = LogManager.getLogger(DeleteEntryCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    OrderService orderService = factory.getOrderService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = ControllerUtils.getUserFromRequest(req);
        if (user == null) {
            throw new PermissionsDeniedException();
        }

        try {
            orderService.deleteOrderEntry(user, Integer.parseInt(req.getParameter("entryId")));

        } catch (IllegalArgumentException e) {
            LOG.error("Error during deleting order Entry", e);
            resp.sendRedirect(ERROR_PAGE);
        }

        resp.sendRedirect("/pharmacy/controller?command=GO_TO_CART");
    }
}
