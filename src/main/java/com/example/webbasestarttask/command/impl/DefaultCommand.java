package com.example.webbasestarttask.command.impl;

import com.example.webbasestarttask.command.Command;
import jakarta.servlet.http.HttpServletRequest;

import static com.example.webbasestarttask.util.Constant.INDEX;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return INDEX;
    }
}
