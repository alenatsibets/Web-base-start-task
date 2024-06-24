package com.example.webbasestarttask.controller.command.impl.user;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static com.example.webbasestarttask.util.constant.PagePath.INDEX;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new Router(INDEX);
    }
}
