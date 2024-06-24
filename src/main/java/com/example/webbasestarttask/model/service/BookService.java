package com.example.webbasestarttask.model.service;

import com.example.webbasestarttask.model.entity.Book;
import com.example.webbasestarttask.model.exception.ServiceException;

import java.util.List;

public interface BookService {
    boolean addBook(String title, String author, String publisher, int year) throws ServiceException;

    boolean deleteBook(String title) throws ServiceException;

    boolean bookExists(String title) throws ServiceException;

    Book findBookByTitle(String title) throws ServiceException;

    List<Book> findAllBooks() throws ServiceException;

    boolean updateBook(String title, Book newBook) throws ServiceException;
}
