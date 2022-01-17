package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.MessageDAO;
import com.epam.training.epharmacy.dao.ProducerDAO;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;
import static com.epam.training.epharmacy.dao.constant.DaoConstants.USER_WORK_PLACE;

public class MessageDAOImpl extends AbstractEntityDAO implements MessageDAO {

    private static final String ADD_MESSAGE_SQL = "INSERT INTO messages (message, date, recipient, sender) " +
            "VALUES (?, ?, (SELECT id FROM users WHERE login=?), (SELECT id FROM users WHERE login=?))";
    private static final String SHOW_MESSAGES_SQL = "SELECT id, message, date, recipient, sender FROM messages WHERE recipient=?";

    private UserDAO userDAO;

    public MessageDAOImpl(UserDAO userDAO) { this.userDAO = userDAO;}

    @Override
    protected String getInitialQuery() {
        return "SELECT id, message, date, recipient, sender FROM messages";
    }

    @Override
    public List<Message> findMessageByCriteria(Criteria<SearchCriteria.Message> criteria) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<Message> messages = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Message message = new Message();
                message.setId(rs.getInt(MESSAGE_ID));
                message.setMessage(rs.getString(MESSAGE_MESSAGE));
                message.setMessageDate(rs.getDate(MESSAGE_DATE));
                message.setRecipient(getUserByLogin(rs.getString(MESSAGE_RECIPIENT)));
                message.setSender(getUserByLogin(rs.getString(MESSAGE_SENDER)));

                messages.add(message);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during finding message", e);
        }finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return messages;
    }

    private User getUserByLogin(String login) throws DAOException, SQLException {
        Criteria<SearchCriteria.User> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
        List<User> users = userDAO.findUserByCriteria(criteria);
        if (!users.isEmpty()) {
            return users.iterator().next();
        }
        return null;
    }

    @Override
    public void saveMessage(Message message) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_MESSAGE_SQL, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, message.getMessage());
            statement.setDate(2, new Date(message.getMessageDate().getTime()));
            statement.setString(3, message.getRecipient().getLogin());
            statement.setString(4, message.getSender().getLogin());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding message", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                message.setId(generatedKeys.getInt(1));
            }
        }
        finally{
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

    @Override
    public List<Message> showMessages(String login) throws DAOException, SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<Message> messages = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(SHOW_MESSAGES_SQL);
            statement.setString(1, login);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Message message = new Message();
                message.setId(rs.getInt(MESSAGE_ID));
                message.setMessage(rs.getString(MESSAGE_MESSAGE));
                message.setMessageDate(rs.getDate(MESSAGE_DATE));
                message.setRecipient(getUserByLogin(rs.getString(MESSAGE_RECIPIENT)));
                message.setSender(getUserByLogin(rs.getString(MESSAGE_SENDER)));

                messages.add(message);
            }

        } catch (SQLException e) {
            throw new DAOException("Error during returning messages list", e);
        }
        finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return messages;
    }
}
