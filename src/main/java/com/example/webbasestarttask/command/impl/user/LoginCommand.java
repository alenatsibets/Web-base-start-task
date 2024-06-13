package com.example.webbasestarttask.command.impl.user;

import com.example.webbasestarttask.command.Command;
import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import com.example.webbasestarttask.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.PagePath.INDEX;
import static com.example.webbasestarttask.util.PagePath.USER_MENU;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_EMAIL;
import static com.example.webbasestarttask.util.ParamConstant.PARAM_PASSWORD;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if (userService.authenticate(email, password)) {
                logger.info("User " + email + " logged in");
                request.setAttribute("user", email);
                session.setAttribute("user_name", email);
                page = USER_MENU;
            } else {
                request.setAttribute("login_msg", "incorrect login or pass");
                page = INDEX;
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            logger.error("User " + email + " cannot log in", e);
            throw new CommandException(e);
        }

        return page;
    }
}
