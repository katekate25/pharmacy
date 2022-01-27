package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface MessageDAO {

    List<Message> findMessageByCriteria(Criteria<SearchCriteria.Message> criteria) throws DAOException;

    void saveMessage(Message message) throws DAOException, SQLException;

    List<Message> getUserMessages(String login) throws DAOException, SQLException;
}
