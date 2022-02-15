package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.User;

public interface PasswordService {

    void setPasswordForUser(User user, String password);

    boolean isPasswordValid(User user, String password);
}
