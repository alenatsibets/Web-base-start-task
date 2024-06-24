package com.example.webbasestarttask.model.service.impl;

import com.example.webbasestarttask.model.dao.impl.BookDaoImpl;
import com.example.webbasestarttask.model.entity.Book;
import com.example.webbasestarttask.model.exception.DaoException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BookServiceImpl implements BookService {
    private static final Logger logger = LogManager.getLogger();
    private static BookServiceImpl instance = new BookServiceImpl();
    private final BookDaoImpl bookDao = BookDaoImpl.getInstance();

    private BookServiceImpl() {
    }

    public static BookServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addBook(String title, String author, String publisher, int year) throws ServiceException {
        boolean inserted;
        Book book = new Book(title, author, publisher, year);
        if (bookExists(title)) {
            throw new ServiceException("User already exists");
        }
        try {
            inserted = bookDao.insert(book);
            logger.info("Service addBook is succeed: ", inserted);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return inserted;
    }

    @Override
    public boolean deleteBook(String title) throws ServiceException {
        boolean deleted;
        try {
            deleted = bookDao.delete(title);
            logger.info("Service deleteBook is succeed: ", deleted);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return deleted;
    }

    @Override
    public boolean bookExists(String title) throws ServiceException {
        Book book;
        try {
            book = bookDao.findByTitle(title);
            logger.info("Service bookExists is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return book != null;
    }

    @Override
    public Book findBookByTitle(String title) throws ServiceException {
        Book book;
        try {
            book = bookDao.findByTitle(title);
            logger.info("Service findBookByTitle is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return book;
    }

    @Override
    public List<Book> findAllBooks() throws ServiceException {
        List<Book> books;
        try {
            books = bookDao.findAll();
            logger.info("Service findAllBooks is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return books;
    }

    @Override
    public boolean updateBook(String title, Book newBook) throws ServiceException {
        boolean updated;
        Book book = findBookByTitle(title);
        int id = book.getId();
        newBook.setId(id);
        try {
            updated = bookDao.update(newBook);
            logger.info("Service updateBook is succeed: ", updated);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return updated;
    }
}
