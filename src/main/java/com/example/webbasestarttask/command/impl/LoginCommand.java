package com.example.webbasestarttask.command.impl;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.Constant.INDEX;
import static com.example.webbasestarttask.util.Constant.MAIN;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("pass");
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login);
                session.setAttribute("user_name", login);
                page = MAIN;
            } else {
                request.setAttribute("login_msg", "incorrect login or pass");
                page = INDEX;
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return page;
    }
}
