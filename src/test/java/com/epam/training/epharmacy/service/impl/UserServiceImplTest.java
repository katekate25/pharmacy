package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.dao.UserDAO;
import com.epam.training.epharmacy.entity.Criteria;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.PasswordService;
import com.epam.training.epharmacy.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    private UserService userService;

    @Mock
    private PasswordService passwordService;
    @Mock
    private UserDAO userDAO;
    @Mock
    private User user;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userService = new UserServiceImpl(userDAO, passwordService);
    }

    @Test
    public void shouldCreateUser() {
        userService.register(user);
        verify(userDAO).saveUser(user);
    }

    @Test
    public void testUpdateUser() {
        userService.updateUser(user);
        verify(userDAO).updateUser(user);
    }

}
