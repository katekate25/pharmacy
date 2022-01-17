package com.epam.training.epharmacy.service.impl;

import com.epam.training.epharmacy.service.SessionService;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.WeakHashMap;

public class SessionServiceImpl implements SessionService {

    private final Map<Thread, HttpSession> sessionMap = new WeakHashMap<>();

    @Override
    public HttpSession getCurrentSession() {
        return null;
    }
}
