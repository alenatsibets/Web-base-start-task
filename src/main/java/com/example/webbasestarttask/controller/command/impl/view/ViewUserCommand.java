package com.example.webbasestarttask.controller.command.impl.view;

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

import static com.example.webbasestarttask.util.constant.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.constant.PagePath.LOGIN;
import static com.example.webbasestarttask.util.constant.PagePath.USER;

public class ViewUserCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(EMAIL) == null) {
            logger.info("User is not logged in");
            return new Router(LOGIN);
        }
        String userEmail = (String) session.getAttribute(EMAIL);
        UserService userService = UserServiceImpl.getInstance();
        User user;
        try {
            user = userService.findUserByEmail(userEmail);
        } catch (ServiceException e) {
            logger.error("User cannot be founded", e);
            throw new CommandException(e);
        }
        session.setAttribute("user", user);
        logger.info("User data returned successfully");
        return new Router(USER);
    }
}