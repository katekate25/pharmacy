package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> findUserByCriteria(Criteria<SearchCriteria.User> criteria) throws DAOException;

    void saveUser(User user) throws DAOException, SQLException;

    void updateUser(User user) throws  DAOException;

    List<User> showUsersByRole (UserRole role) throws DAOException, SQLException;

}
