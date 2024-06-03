package com.example.webbasestarttask.command;

import com.example.webbasestarttask.command.impl.AddUserCommand;
import com.example.webbasestarttask.command.impl.DefaultCommand;
import com.example.webbasestarttask.command.impl.LoginCommand;
import com.example.webbasestarttask.command.impl.LogoutCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    DEFAULT(new DefaultCommand());
    Command command;
    CommandType(Command command) {
        this.command = command;
    }
    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
