package com.example.webbasestarttask.controller.command;

import com.example.webbasestarttask.controller.command.impl.book.AddBookCommand;
import com.example.webbasestarttask.controller.command.impl.book.DeleteBookCommand;
import com.example.webbasestarttask.controller.command.impl.user.*;
import com.example.webbasestarttask.controller.command.impl.view.*;

public enum CommandType {
    VIEW_MENU(new ViewMenuCommand()),
    VIEW_LOGIN(new ViewLoginCommand()),
    VIEW_CHANGE_PASSWORD(new ViewChangePasswordCommand()),
    VIEW_SIGN_UP(new ViewSignUpCommand()),
    VIEW_ADD_BOOK(new ViewAddBookCommand()),
    VIEW_ALL_BOOKS(new ViewAllBooksCommand()),
    VIEW_DELETE_BOOK(new ViewDeleteBookCommand()),
    CHANGE_PASSWORD(new ChangePasswordCommand()),
    VERIFICATION(new VerificationCommand()),
    DELETE_USER(new DeleteUserCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTER(new RegisterCommand()),
    VIEW_USER(new ViewUserCommand()),
    ADD_BOOK(new AddBookCommand()),
    DELETE_BOOK(new DeleteBookCommand()),
    FILE_UPLOAD(new FileUploadCommand());
    Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public static Command define(String commandStr) {
        CommandType current = CommandType.valueOf(commandStr.toUpperCase());
        return current.command;
    }
}
