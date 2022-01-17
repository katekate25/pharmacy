package com.epam.training.epharmacy.service;

import com.epam.training.epharmacy.entity.Message;
import com.epam.training.epharmacy.entity.Producer;
import com.epam.training.epharmacy.entity.User;

import java.util.List;

public interface MessageService {

    void sendMessage(Message message);

    List<Message> showMessagesToUser(User user);

    User getUserByLogin(String login);
}
