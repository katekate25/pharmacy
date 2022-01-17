package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import com.epam.training.epharmacy.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();

        User user = new User();
        user.setUserRole(UserRole.CUSTOMER);
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setFullName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setTelNumber(req.getParameter("telNumber"));


        try {
            userService.register(user);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE&userExists=true");
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        resp.sendRedirect("/pharmacy/controller?command=GO_TO_MAIN_PAGE");
    }

}

