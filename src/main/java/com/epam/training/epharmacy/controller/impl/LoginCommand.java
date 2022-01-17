package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
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

public class LoginCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException {

        Logger logger = LogManager.getLogger(LoginCommand.class);

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        try {
            User user = userService.authorization(login, password);

            if (user != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);
                String userName = user.getFullName();
                int userId = user.getId();
                String role = user.getUserRole().toString();
                session.setAttribute("username", req.getParameter("userName"));
                session.setAttribute("userid", req.getParameter("userId"));
                session.setAttribute("role", req.getParameter("role"));
                session.setAttribute("login", req.getParameter("login"));
                resp.sendRedirect(req.getContextPath() + "/controller?command=GO_TO_MAIN_PAGE");
            } else {
                resp.sendRedirect(req.getContextPath() + "/controller?command=GO_TO_LOGIN_PAGE&wrongUser=true");
            }

        } catch (ServiceException e){
            logger.error("Problems on logging", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
        }
    }
}
