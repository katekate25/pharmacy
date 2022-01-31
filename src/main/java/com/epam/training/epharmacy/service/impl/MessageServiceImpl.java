package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MessageDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.MessageService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    private final Logger LOG = LogManager.getLogger(MessageServiceImpl.class);

    public void sendMessage(Message message) {
        DAOFactory factory = DAOFactory.getInstance();
        MessageDAO messageDAO = factory.getMessageDAO();

        try {
            messageDAO.saveMessage(message);

        } catch (DAOException | SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Message> showMessagesToUser(User user) {
        DAOFactory factory = DAOFactory.getInstance();
        MessageDAO messageDAO = factory.getMessageDAO();
        Criteria<SearchCriteria.Message> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.Message.RECIPIENT, user.getId());
        try {

            return messageDAO.findMessageByCriteria(criteria);

        } catch (DAOException e) {
            LOG.error("Error during returning messages for user", e);
            throw new ServiceException(e);
        }
    }

}
