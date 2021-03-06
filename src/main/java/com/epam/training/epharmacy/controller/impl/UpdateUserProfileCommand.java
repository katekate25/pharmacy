package com.epam.training.epharmacy.controller.impl;

import com.epam.training.epharmacy.controller.Command;
import com.epam.training.epharmacy.controller.constant.ControllerConstants;
import com.epam.training.epharmacy.controller.exception.PermissionsDeniedException;
import com.epam.training.epharmacy.controller.util.ControllerUtils;
import com.epam.training.epharmacy.entity.User;
import com.epam.training.epharmacy.service.PasswordService;
import com.epam.training.epharmacy.service.UserService;
import com.epam.training.epharmacy.service.exception.ServiceException;
import com.epam.training.epharmacy.service.factory.ServiceFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserProfileCommand implements Command {

    private final Logger LOG = LogManager.getLogger(UpdateUserProfileCommand.class);
    private final UserService userService = ServiceFactory.getInstance().getUserService();
    private final PasswordService passwordService = ServiceFactory.getInstance().getPasswordService();

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User currentUser = ControllerUtils.getUserFromRequest(req);
        if (currentUser == null) {
            throw new PermissionsDeniedException();
        }

        currentUser.setFullName(req.getParameter("userName"));
        currentUser.setEmail(req.getParameter("email"));
        currentUser.setTelNumber(req.getParameter("telNumber"));
        String password = req.getParameter("password");
        if (StringUtils.isNotBlank(password))
        {
            passwordService.setPasswordForUser(currentUser,password);
        }
        currentUser.setWorkPlace(req.getParameter("workPlace"));

        try {
            userService.updateUser(currentUser);
            HttpSession session = req.getSession(true);
            session.setAttribute("user", currentUser);
            resp.sendRedirect(req.getContextPath() + "/controller?command=GO_TO_PERSONAL_CABINET&successUpdate=true");
        } catch (ServiceException e){
            LOG.error("Error during updating user profile", e);
            resp.sendRedirect(ControllerConstants.ERROR_PAGE);
        }
    }
}
