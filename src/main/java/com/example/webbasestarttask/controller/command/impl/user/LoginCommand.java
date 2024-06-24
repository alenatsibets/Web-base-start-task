package com.example.webbasestarttask.controller.command.impl.user;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.exception.CommandException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.service.UserService;
import com.example.webbasestarttask.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.GregorianCalendar;

import static com.example.webbasestarttask.util.constant.AttributeConstant.*;
import static com.example.webbasestarttask.util.constant.PagePath.*;
import static com.example.webbasestarttask.util.constant.ParamConstant.PARAM_EMAIL;
import static com.example.webbasestarttask.util.constant.ParamConstant.PARAM_PASSWORD;

public class LoginCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String email = request.getParameter(PARAM_EMAIL);
        String password = request.getParameter(PARAM_PASSWORD);
        UserService userService = UserServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        String time = "Login time: " + gregorianCalendar.getTime();
        session.setAttribute(TIME, time);
        try {
            if (userService.authenticate(email, password)) {
                logger.info("User " + email + " logged in");
                request.setAttribute(EMAIL, email);
                session.setAttribute(EMAIL, email);
                Cookie emailCookie = new Cookie(EMAIL, email);
                emailCookie.setMaxAge(60 * 60 * 24 * 7);
                response.addCookie(emailCookie);
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

        return new Router(page);
    }
}
