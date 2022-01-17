package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.Message;
import com.epam.training.epharmacy.service.MessageService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateRequestForPrescriptionCommand implements Command {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException, DAOException, ServiceException, DAOException, ParseException {
        Logger logger = LogManager.getLogger(CreateRequestForPrescriptionCommand.class);

        HttpSession session = req.getSession(false);

        String recipient = req.getParameter("recipient");
        String sender = session.getAttribute("login").toString();

        ServiceFactory factory = ServiceFactory.getInstance();
        UserService userService = factory.getUserService();
        MessageService messageService = factory.getMessageService();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        Message message = new Message();
        message.setMessage(req.getParameter("message"));
        message.setMessageDate(date);
        message.setRecipient(messageService.getUserByLogin(recipient));
        message.setSender(messageService.getUserByLogin(sender));

        try {

          messageService.sendMessage(message);


        } catch (ServiceException e){
            logger.error("Problems on sending message", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_ERROR_PAGE");
        }
        resp.sendRedirect("/pharmacy/controller?command=GO_TO_DOCTOR_PERSONAL_PAGE&sendSuccessful=true");
    }
}
