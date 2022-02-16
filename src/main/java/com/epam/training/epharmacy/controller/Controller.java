package com.epam.training.epharmacy.controller;

import com.epam.training.epharmacy.controller.constant.ControllerConstants;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final Logger LOG = LogManager.getLogger(Controller.class);
    private final CommandProvider provider = new CommandProvider();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String commandName = req.getParameter("command");
            Command command = provider.getCommand(commandName);
            resp.setCharacterEncoding(ControllerConstants.UTF_8);
            command.execute(req, resp);
        } catch (PermissionsDeniedException e) {
            LOG.error("User doesn't have permissions to perform operation {} with params {}",
                    req.getContextPath(), req.getParameterMap(), e);
            resp.sendRedirect(ControllerConstants.LOGIN_PAGE);
        } catch (Throwable t) {
            LOG.error("Something went wrong during request to {} and params {}",
                    req.getContextPath(), req.getParameterMap(), t);
            throw new ServletException(t);
        }
    }
}
