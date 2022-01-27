package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToNotFoundErrorPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/error404Page.jsp");
        dispatcher.forward(req, resp);
    }
}
