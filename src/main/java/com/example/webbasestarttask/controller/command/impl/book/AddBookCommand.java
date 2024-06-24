package com.example.webbasestarttask.controller.command.impl.book;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.exception.CommandException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.service.BookService;
import com.example.webbasestarttask.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.webbasestarttask.util.constant.PagePath.*;
import static com.example.webbasestarttask.util.constant.ParamConstant.*;

public class AddBookCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String title = request.getParameter(PARAM_TITLE);
        String author = request.getParameter(PARAM_AUTHOR);
        String publisher = request.getParameter(PARAM_PUBLISHER);
        int year = Integer.parseInt(request.getParameter(PARAM_YEAR));
        BookService bookService = BookServiceImpl.getInstance();
        String page;
        HttpSession session = request.getSession();
        try {
            if (bookService.addBook(title, author, publisher, year)) {
                logger.info("Book created successfully");
                page = ADD_BOOK_RESULT;
            } else {
                request.setAttribute("error", "something went wrong");
                page = ADD_BOOK;
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            logger.error("Book cannot to be created", e);
            throw new CommandException(e);
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}
