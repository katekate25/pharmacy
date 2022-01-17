package com.epam.training.epharmacy.service;


import com.epam.training.epharmacy.dao.exception.DAOException;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.exception.ServiceException;

import java.util.List;

public interface UserService {

    User authorization(String login, String password) throws ServiceException, DAOException;

    void register(User user);

    User getCurrentUser();

    List<User> showDoctors();
}
