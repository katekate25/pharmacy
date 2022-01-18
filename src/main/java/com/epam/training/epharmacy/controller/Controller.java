package com.epam.training.epharmacy.controller;

import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger LOG = LogManager.getLogger(Controller.class);
    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Throwable t) {
            LOG.error("Something went wrong during request to {}", req.getContextPath(), t);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            process(req, resp);
        } catch (Throwable t) {
            LOG.error("Something went wrong during request to {}", req.getContextPath(), t);
        }
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, ServletException, DAOException, ServiceException, ParseException {
        String commandName = req.getParameter("command");
        Command command = provider.getCommand(commandName);
        resp.setCharacterEncoding("UTF-8");
        command.execute(req, resp);
    }
}
