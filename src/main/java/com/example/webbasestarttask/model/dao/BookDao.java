package com.example.webbasestarttask.model.dao;

import com.example.webbasestarttask.model.entity.Book;
import com.example.webbasestarttask.model.exception.DaoException;

public interface BookDao {
    boolean delete(String title) throws DaoException;

    Book findByTitle(String title) throws DaoException;
}
