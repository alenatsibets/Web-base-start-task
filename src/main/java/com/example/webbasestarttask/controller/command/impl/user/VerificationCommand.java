package com.example.webbasestarttask.controller.command.impl.user;


import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.entity.User;
import com.example.webbasestarttask.model.exception.CommandException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.service.UserService;
import com.example.webbasestarttask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.constant.AttributeConstant.*;

public class VerificationCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String userVerificationCode = request.getParameter(VERIFICATION_CODE);
        UserService userService = UserServiceImpl.getInstance();
        HttpSession session = request.getSession();
        String verificationCode = (String) session.getAttribute(VERIFICATION_CODE);
        User user = (User) session.getAttribute(USER);
        String email = user.getEmail();
        String page;
        if (verificationCode.equals(userVerificationCode)) {
            user.setVerified(true);
            try {
                userService.updateStatus(user.isVerified(), email);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            page = "view_registration_result";
        } else {
            request.setAttribute("error", "invalid verification code");
            page = "view_verification_page";
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}