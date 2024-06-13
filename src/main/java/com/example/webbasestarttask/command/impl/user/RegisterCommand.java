package com.example.webbasestarttask.command.impl.user;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.PagePath.*;
import static com.example.webbasestarttask.util.ParamConstant.*;

public class RegisterCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter(PARAM_USERNAME);
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            userService.register(username, email, password);
            logger.info("User created successfully");
            page = REGISTRATION_SUCCESSFUL;
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            logger.error("User cannot to be created", e);
            throw new CommandException(e);
        }
        return page;
    }
}
