package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.*;
import com.epam.training.epharmacy.service.PasswordService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final Logger LOG = LogManager.getLogger(UserServiceImpl.class);
    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
    private final PasswordService passwordService;

    public UserServiceImpl(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public User authorization(String login, String password) throws ServiceException, DAOException {
        User user = null;
        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
            List<User> users = userDAO.findUserByCriteria(criteria);
            if (CollectionUtils.isNotEmpty(users)) {
                user = users.iterator().next();
            }
        } catch (DAOException e){
            LOG.error("Error during authorization", e);
            throw new ServiceException(e);
        }
        if (!passwordService.isPasswordValid(user, password)) {
            throw new IllegalArgumentException("Password not valid");
        }
        return user;
    }

    @Override
    public void register(User user) {
        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, user.getLogin());
            List<User> users = userDAO.findUserByCriteria(criteria);
            if (users != null && !users.isEmpty()) {
                throw new IllegalArgumentException("Such user already exists");
            }
            userDAO.saveUser(user);

        } catch (DAOException | SQLException e){
            LOG.error("Error during registration", e);
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<User> showDoctors() {
        try {
            return userDAO.showUsersByRole(UserRole.DOCTOR);

        } catch (DAOException | SQLException e){
            LOG.error("Error during showing doctors list", e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public List<User> showCustomers() {
        List<User> users = null;

        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.ROLES_CODE, UserRole.CUSTOMER);
            return userDAO.findUserByCriteria(criteria);

        } catch (DAOException e){
            LOG.error("Error during showing customers list", e);
            throw  new ServiceException(e);
        }
    }

    @Override
    public User getUserByLogin(String login) {
        List<User> users = null;
        User user = null;
        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
            users = userDAO.findUserByCriteria(criteria);
            if(CollectionUtils.isNotEmpty(users)){
                return users.iterator().next();
            }
        } catch (DAOException e) {
            throw new ServiceException("Error during finding user", e);
        }
        return null;
    }


    @Override
    public void updateUser(User user) {
        try {
            userDAO.updateUser(user);
        } catch (DAOException e) {
            LOG.error("Error during user update", e);
            throw new ServiceException(e);
        }
    }

}
