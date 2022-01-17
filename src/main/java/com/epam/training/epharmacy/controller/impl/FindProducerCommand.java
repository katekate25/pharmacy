package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.service.MedicinesService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class FindProducerCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {

        Logger logger = LogManager.getLogger(FindProducerCommand.class);

        ServiceFactory factory = ServiceFactory.getInstance();
        MedicinesService medicinesService = factory.getMedicinesService();
        String name = req.getParameter("producer");

        try {
            req.setAttribute("producerName", medicinesService.getProducerByName(name));

        }catch (ServiceException e) {
            logger.error("Problems on finding producer", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
        }


    }
}
