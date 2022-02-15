package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.entity.UserRole;
import com.epam.training.epharmacy.service.PasswordService;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import com.epam.training.epharmacy.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.epam.training.epharmacy.controller.constant.ControllerConstants.LOGIN_PAGE;

public class RegistrationCommand implements Command {

    private final Logger LOG = LogManager.getLogger(RegistrationCommand.class);

    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final PasswordService passwordService = ServiceFactory.getInstance().getPasswordService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser != null && currentUser.getUserRole() != UserRole.PHARMACIST)
        {
            throw new PermissionsDeniedException();
        }

        try {
            User user = new User();
            user.setUserRole(currentUser == null ? UserRole.CUSTOMER : UserRole.DOCTOR);
            user.setLogin(req.getParameter("login"));
            passwordService.setPasswordForUser(user, req.getParameter("password"));
            user.setFullName(req.getParameter("name"));
            user.setEmail(req.getParameter("email"));
            user.setTelNumber(req.getParameter("telNumber"));
            user.setAge(Integer.parseInt(req.getParameter("age")));
            user.setWorkPlace(req.getParameter("workPlace"));
            user.setSpecialization(req.getParameter("specialization"));
            userService.register(user);
            resp.sendRedirect(LOGIN_PAGE);

        } catch (IllegalArgumentException e) {
            LOG.error("Error during registration", e);
            resp.sendRedirect("/pharmacy/controller?command=GO_TO_REGISTRATION_PAGE&userExists=true");
        }

    }

}

