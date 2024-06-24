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

public class DeleteBookCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String title = request.getParameter(PARAM_TITLE);
        BookService bookService = BookServiceImpl.getInstance();
        try {
            bookService.findBookByTitle(title);
        } catch (ServiceException e) {
            logger.error("Book cannot to be founded", e);
            throw new CommandException(e);
        }
        String page;
        HttpSession session = request.getSession();
        try {
            if (bookService.deleteBook(title)) {
                logger.info("Book deleted successfully");
                page = DELETE_BOOK_RESULT;
            } else {
                request.setAttribute("error", "something went wrong");
                page = DELETE_BOOK;
            }
            session.setAttribute("current_page", page);
        } catch (ServiceException e) {
            logger.error("Book cannot to be deleted", e);
            throw new CommandException(e);
        }
        return new Router(page, Router.RouteType.REDIRECT);
    }
}
