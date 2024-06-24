package com.example.webbasestarttask.controller.command.impl.view;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.example.webbasestarttask.util.constant.PagePath.REGISTRATION_SUCCESSFUL;

public class ViewRegistrationResultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException {
        return new Router(REGISTRATION_SUCCESSFUL);
    }
}
