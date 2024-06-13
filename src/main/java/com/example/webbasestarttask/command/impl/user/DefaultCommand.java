package com.example.webbasestarttask.command.impl.user;

import com.example.webbasestarttask.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.webbasestarttask.util.PagePath.INDEX;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return INDEX;
    }
}
