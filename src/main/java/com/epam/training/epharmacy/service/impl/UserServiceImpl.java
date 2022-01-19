package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.controller.impl.AddPrescriptionCommand;
import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.SearchCriteria;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final Logger LOG = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User authorization(String login, String password) throws ServiceException, DAOException {

        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        User user = null;

        try {
            Criteria<SearchCriteria.User> criteria = new Criteria<>();
            criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
            List<User> users = userDAO.findUserByCriteria(criteria);
            if (users != null && !users.isEmpty() && users.iterator().next().getPassword().equals(password)) {
                user = users.iterator().next();
            }
        } catch (DAOException e){
            LOG.error("Error during authorization", e);
            throw  new ServiceException(e);
        }
        return user;
    }

    @Override
    public void register(User user) {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();

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
    public User getCurrentUser() {
        return null;
    }

    @Override
    public List<User> showDoctors() {
        DAOFactory factory = DAOFactory.getInstance();
        UserDAO userDAO = factory.getUserDAO();
        try {
            return userDAO.showUsersByRole(UserRole.DOCTOR);

        } catch (DAOException | SQLException e){
            LOG.error("Error during showing doctors list", e);
            throw  new ServiceException(e);
        }
    }

}
