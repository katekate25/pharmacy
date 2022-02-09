package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import com.epam.training.epharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.ERROR_PAGE;

public class LoginCommand implements Command {

    private final Logger LOG = LogManager.getLogger(LoginCommand.class);
    ServiceFactory factory = ServiceFactory.getInstance();
    UserService userService = factory.getUserService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User user = userService.authorization(login, password);
           // String.valueOf(ControllerUtils.generateHash(password))
            if (user != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);
                session.setAttribute("username", req.getParameter("userName"));
                session.setAttribute("userid", req.getParameter("userId"));
                session.setAttribute("role", req.getParameter("role"));
                session.setAttribute("login", login);
                resp.sendRedirect(req.getContextPath() + "/controller?command=GO_TO_GREETING_PAGE");
            } else {
                resp.sendRedirect(req.getContextPath() + "/controller?command=GO_TO_LOGIN_PAGE&wrongUser=true");
            }

        } catch (ServiceException e){
            LOG.error("Error during logging", e);
            resp.sendRedirect(ERROR_PAGE);
        }
    }
}
