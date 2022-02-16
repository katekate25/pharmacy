package com.epam.training.epharmacy.dao;

import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.*;

import java.util.List;

public interface UserDAO {

    List<User> findUserByCriteria(Criteria<SearchCriteria.User> criteria) throws DAOException;

    void saveUser(User user) throws DAOException;

    void updateUser(User user) throws  DAOException;

}
