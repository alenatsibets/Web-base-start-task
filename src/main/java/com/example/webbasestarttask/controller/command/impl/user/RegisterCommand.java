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

import static com.example.webbasestarttask.util.constant.AttributeConstant.*;
import static com.example.webbasestarttask.util.constant.PagePath.VERIFICATION_PAGE;

public class RegisterCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        boolean isVerified = false;
        UserService userService = UserServiceImpl.getInstance();

        try {
            userService.register(username, email, password, isVerified);
            User user = new User(username, email, password, isVerified);
            logger.info(user);
            request.getSession().setAttribute(USER, user);
            String verificationCode = userService.verification(email);
            logger.info(verificationCode);
            request.getSession().setAttribute(VERIFICATION_CODE, verificationCode);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ERROR, e.getCause());
        }
        return new Router(VERIFICATION_PAGE, Router.RouteType.REDIRECT);
    }
}
