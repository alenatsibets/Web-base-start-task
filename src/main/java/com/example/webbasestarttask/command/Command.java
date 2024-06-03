package com.example.webbasestarttask.command;

import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
    default void refresh(){}
}
