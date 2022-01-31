package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.service.UserService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class GoToInvoicesPageCommand implements Command {
    private final Logger LOG = LogManager.getLogger(GoToInvoicesPageCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    UserService userService = factory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date begin = formatter.parse(req.getParameter("begin"));
            Date end = formatter.parse(req.getParameter("end"));
//            if (StringUtils.isNotBlank(begin) && StringUtils.isNotBlank(end)) {
//                req.setAttribute("foundMedicinesByDate",  factory.getMedicinesService().medicineByIncome(begin, end);
//            }
           // req.setAttribute("invoices",);userService.showCustomers());

            String client = req.getParameter("client");
            if (StringUtils.isNotBlank(client)) {
                req.setAttribute("customerByLogin", userService.getUserByLogin(client));
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/invoices.jsp");
            dispatcher.forward(req, resp);
        } catch (ServiceException | ParseException e){
            LOG.error("Error during returning customers list", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
