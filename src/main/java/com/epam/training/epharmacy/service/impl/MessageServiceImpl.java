package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.MessageDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.MessageService;
import com.epam.training.epharmacy.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService {

    public void sendMessage(Message message){
        DAOFactory factory = DAOFactory.getInstance();
        MessageDAO messageDAO = factory.getMessageDAO();

        try {
            messageDAO.saveMessage(message);

        } catch (DAOException | SQLException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Message> showMessagesToUser(User user) {
        DAOFactory factory = DAOFactory.getInstance();
        MessageDAO messageDAO = factory.getMessageDAO();

        try {

            return messageDAO.showMessages(user.getLogin());

        } catch (DAOException | SQLException e){
            // TODO log exception
            throw  new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        List<User> users = null;

        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
            users = userDAO.findUserByCriteria(criteria);
        } catch (DAOException e){
            throw  new ServiceException(e);
        }
        return users.iterator().next();
    }
}
