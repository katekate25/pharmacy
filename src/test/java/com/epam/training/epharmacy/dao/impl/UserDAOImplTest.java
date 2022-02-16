package com.epam.training.epharmacy.dao.impl;

import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.dao.factory.DAOFactory;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.SearchCriteria;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserDAOImplTest extends AbstractDAOTest{

    private static final String SERGEEV_USER_LOGIN = "sergeev_p";
    private static final String LOGIN = "login";
    private static final String FULL_NAME = "full name";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email@epam.com";
    private static final Integer AGE = 25;
    private static final String TEL_NUMBER = "+223 2323 32232";

    private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    @Test
    public void shouldFindUserByLogin() {
        User user = getUserByLogin(SERGEEV_USER_LOGIN);
        assertEquals(SERGEEV_USER_LOGIN, user.getLogin());
    }

    @Test
    public void shouldUpdateUserName() {
        final String USER_FULL_NAME = "Peter Sergeev";
        final String UPDATED_USER_FULL_NAME = "Peter Sergeev-Ivanov";
        User user = getUserByLogin(SERGEEV_USER_LOGIN);
        assertEquals(USER_FULL_NAME, user.getFullName());
        user.setFullName(UPDATED_USER_FULL_NAME);
        userDAO.updateUser(user);
        user = getUserByLogin(SERGEEV_USER_LOGIN);
        assertEquals(UPDATED_USER_FULL_NAME, user.getFullName());
    }

    @Test
    public void shouldAddNewUser() {
        User user = new User();
        user.setLogin(LOGIN);
        user.setFullName(FULL_NAME);
        user.setPassword(PASSWORD);
        user.setEmail(EMAIL);
        user.setAge(AGE);
        user.setTelNumber(TEL_NUMBER);
        user.setUserRole(UserRole.DOCTOR);
        userDAO.saveUser(user);
        User newUser = getUserByLogin(LOGIN);
        assertNotNull(newUser);
        assertEquals(FULL_NAME, newUser.getFullName());
        assertEquals(PASSWORD, newUser.getPassword());
        assertEquals(EMAIL, newUser.getEmail());
        assertEquals(AGE, newUser.getAge());
        assertEquals(TEL_NUMBER, newUser.getTelNumber());
        assertEquals(UserRole.DOCTOR, newUser.getUserRole());
    }

    private User getUserByLogin(String login) {
        Criteria<SearchCriteria.User> criteria = new Criteria<>();
        criteria.getParametersMap().put(SearchCriteria.User.LOGIN, login);
        List<User> users = userDAO.findUserByCriteria(criteria);
        assertNotNull(users);
        assertEquals(1, users.size());
        return users.iterator().next();
    }
}
