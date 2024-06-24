package com.example.webbasestarttask.controller.command.impl.view;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.constant.AttributeConstant.EMAIL;
import static com.example.webbasestarttask.util.constant.PagePath.LOGIN;
import static com.example.webbasestarttask.util.constant.PagePath.USER_MENU;

public class ViewMenuCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(EMAIL) == null) {
            logger.info("User is not logged in");
            return new Router(LOGIN);
        }
        return new Router(USER_MENU);
    }
}
