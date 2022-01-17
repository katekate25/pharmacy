package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.entity.Producer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class GoToGreetingPage implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {

        List<Producer> producers = new ArrayList<>();
        Producer producer = new Producer();
        producer.setProducerFactoryName("Sun");
        producer.setProducerCountry("Germany");
        producers.add(producer);

        req.setAttribute("producers", producers);

        HttpSession session = req.getSession();
        String url = req.getRequestURL().toString();
        session.setAttribute("url", url);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/greetingPage.jsp");
        dispatcher.forward(req, resp);
    }
}
