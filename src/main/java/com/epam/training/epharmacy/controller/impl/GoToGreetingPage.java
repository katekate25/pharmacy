package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class GoToGreetingPage implements Command {

    private final Logger LOG = LogManager.getLogger(GoToGreetingPage.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        String url = req.getRequestURL().toString();
        session.setAttribute("url", url);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/greetingPage.jsp");
        dispatcher.forward(req, resp);
    }
}
