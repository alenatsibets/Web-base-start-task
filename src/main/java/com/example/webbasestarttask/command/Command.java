package com.example.webbasestarttask.command;

import com.example.webbasestarttask.exception.CommandException;
import com.example.webbasestarttask.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@FunctionalInterface
public interface Command {
    Logger logger = LogManager.getLogger();
    String execute(HttpServletRequest request) throws CommandException;
    default void refresh(){}
}
