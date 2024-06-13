package com.example.webbasestarttask.command;

import com.example.webbasestarttask.command.impl.contact.CreateContactCommand;
import com.example.webbasestarttask.command.impl.user.*;
import com.example.webbasestarttask.command.impl.user.ShowUserCommand;

public enum CommandType {
    ADD_USER(new AddUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    UPDATE_USERNAME(new UpdateUsernameCommand()),
    SHOW_USER(new ShowUserCommand()),
    CREATE_CONTACT(new CreateContactCommand()),
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
