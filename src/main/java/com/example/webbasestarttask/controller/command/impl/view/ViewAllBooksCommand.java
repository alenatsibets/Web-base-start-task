package com.example.webbasestarttask.controller.command.impl.view;

import com.example.webbasestarttask.controller.command.Command;
import com.example.webbasestarttask.controller.command.Router;
import com.example.webbasestarttask.model.entity.Book;
import com.example.webbasestarttask.model.exception.CommandException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.service.BookService;
import com.example.webbasestarttask.model.service.impl.BookServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static com.example.webbasestarttask.util.constant.AttributeConstant.*;
import static com.example.webbasestarttask.util.constant.PagePath.*;

public class ViewAllBooksCommand implements Command {
    private static final int ROWS_PER_PAGE = 5;

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute(EMAIL) == null) {
            logger.info("User is not logged in");
            return new Router(LOGIN);
        }
        BookService bookService = BookServiceImpl.getInstance();
        List<Book> books;
        try {
            books = bookService.findAllBooks();
        } catch (ServiceException e) {
            logger.error("Books cannot be founded", e);
            throw new CommandException(e);
        }
        int totalPages = (int) Math.ceil((double) books.size() / ROWS_PER_PAGE);
        session.setAttribute(BOOK_LIST, books);

        String pageStr = request.getParameter("page");
        int currentPage = pageStr == null ? 1 : Integer.parseInt(pageStr);
        int start = (currentPage - 1) * ROWS_PER_PAGE;
        int end = Math.min(start + ROWS_PER_PAGE, books.size());
        List<Book> pageData = books.subList(start, end);
        request.setAttribute("booksList", pageData);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        logger.info("Book list returned");
        return new Router(BOOKS);
    }
}
