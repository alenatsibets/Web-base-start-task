package com.example.webbasestarttask.model.dao.impl;

import com.example.webbasestarttask.model.dao.BaseDao;
import com.example.webbasestarttask.model.dao.BookDao;
import com.example.webbasestarttask.model.entity.Book;
import com.example.webbasestarttask.model.exception.DaoException;
import com.example.webbasestarttask.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, publisher, year) VALUES (?, ?, ?, ?);";
    private static final String DELETE_BOOOK = "DELETE FROM books WHERE title = ?;";
    private static final String UPDATE_BOOK = "UPDATE books SET title = ?, author = ?, publisher = ?, year = ? WHERE book_id = ?;";
    private static final String SELECT_BOOK_BY_TITLE = "SELECT book_id, title, author, publisher, year FROM books WHERE title = ?";
    private static final String SELECT_ALL_BOOKS = "SELECT book_id, title, author, publisher, year FROM books";

    private static final BookDaoImpl instance = new BookDaoImpl();

    private BookDaoImpl() {
    }

    public static BookDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Book book) throws DaoException {
        boolean inserted = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOK);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.executeUpdate();
            inserted = true;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO insert is succeed: ");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return inserted;
    }

    @Override
    public boolean delete(Book book) throws DaoException {
        String title = book.getTitle();
        return delete(title);
    }

    @Override
    public boolean delete(String title) throws DaoException {
        boolean deleted;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_BOOOK);
            statement.setString(1, title);
            deleted = statement.executeUpdate() > 0;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO delete is succeed:" + deleted);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return deleted;
    }

    @Override
    public List<Book> findAll() throws DaoException {
        List<Book> books = new ArrayList<>();
        Book book;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOKS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                int year = resultSet.getInt("year");
                book = new Book(id, title, author, publisher, year);
                books.add(book);
            }
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO select all is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return books;
    }

    @Override
    public Book findByTitle(String title) throws DaoException {
        Book book = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_TITLE);
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("book_id");
                String author = resultSet.getString("author");
                String publisher = resultSet.getString("publisher");
                int year = resultSet.getInt("year");
                book = new Book(id, title, author, publisher, year);
            }
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO select by email is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return book;
    }

    @Override
    public boolean update(Book book) throws DaoException {
        boolean updated;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK);
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getPublisher());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getId());
            updated = statement.executeUpdate() > 0;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO update is succeed: " + updated);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return updated;
    }
}
