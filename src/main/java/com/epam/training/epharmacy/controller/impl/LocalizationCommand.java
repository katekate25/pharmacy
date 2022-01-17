package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class LocalizationCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        req.getSession(true).setAttribute("local", req.getParameter("local"));
        //req.getSession().invalidate();
        String url = req.getHeader("Referer");
        resp.sendRedirect(url);
    }
}
