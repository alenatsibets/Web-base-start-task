package com.example.webbasestarttask.controller.command;

import com.example.webbasestarttask.model.exception.CommandException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@FunctionalInterface
public interface Command {
    Logger logger = LogManager.getLogger();

    Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException, ServletException, IOException;

    default void refresh() {
    }
}
