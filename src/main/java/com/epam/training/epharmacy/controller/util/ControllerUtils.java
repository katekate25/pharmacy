package com.epam.training.epharmacy.controller.util;

import com.epam.training.epharmacy.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ControllerUtils {

    private ControllerUtils() {
    }

    public static User getUserFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if (session != null) {
           return (User) session.getAttribute("user");
        }
        return null;
    }
}
