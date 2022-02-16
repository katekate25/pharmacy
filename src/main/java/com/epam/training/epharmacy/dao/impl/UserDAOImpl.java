package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.connection.ConnectionPool;
import com.epam.training.epharmacy.dao.connection.ConnectionPoolException;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.epharmacy.dao.constant.DaoConstants.*;

public class UserDAOImpl extends AbstractEntityDAO implements UserDAO {

    private static final String ADD_USER_SQL = "INSERT INTO pharmacy.users (name, login, password, roles_code, email, telephone_number, age, work_place, specialization, salt) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE pharmacy.users SET name = ?, password = ?, email = ?, telephone_number = ?, age = ?, work_place = ? WHERE login = ?";

    @Override
    protected String getInitialQuery() {
        return "SELECT id, name, login, password, roles_code, email, telephone_number, age, work_place, specialization, salt FROM pharmacy.users";
    }

    @Override
    public List<User> findUserByCriteria(Criteria<SearchCriteria.User> criteria) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = createStatement(connection, criteria);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(USER_ID));
                user.setFullName(rs.getString(USER_NAME));
                user.setLogin(rs.getString(USER_LOGIN));
                user.setPassword(rs.getString(USER_PASSWORD));
                user.setUserRole(UserRole.valueOf(rs.getString(ROLE_CODE)));
                user.setEmail(rs.getString(USER_EMAIL));
                user.setTelNumber(rs.getString(USER_TELEPHONE_NUMBER));
                user.setAge(rs.getInt(USER_AGE));
                user.setSpecialization(rs.getString(USER_SPECIALIZATION));
                user.setWorkPlace(rs.getString(USER_WORK_PLACE));
                user.setSalt(rs.getString(SALT));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("Error during finding user", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
        return users;
    }

    @Override
    public void saveUser(User user) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_USER_SQL, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserRole().name());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getTelNumber());
            statement.setInt(7, user.getAge());
            statement.setString(8, user.getWorkPlace());
            statement.setString(9, user.getSpecialization());
            statement.setString(10, user.getSalt());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during adding user", e);
        }
        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        }catch (SQLException e) {
            throw new DAOException("Error during getting user id", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }


    @Override
    public void updateUser(User user) throws DAOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getTelNumber());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getWorkPlace());
            statement.setString(7, user.getLogin());

            statement.executeUpdate();
        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Error during updating user", e);
        } finally {
            ConnectionPool.getInstance().closeConnection(connection, statement);
        }
    }

}
