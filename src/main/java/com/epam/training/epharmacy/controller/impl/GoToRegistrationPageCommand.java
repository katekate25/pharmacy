package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class GoToRegistrationPageCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        resp.getWriter().print("welcome");


        HttpSession session = req.getSession();
        String url = req.getRequestURL().toString();
        session.setAttribute("url", url);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/registration.jsp");
        dispatcher.forward(req, resp);
    }
}
