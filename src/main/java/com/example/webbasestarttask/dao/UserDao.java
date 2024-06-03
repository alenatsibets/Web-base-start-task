package com.example.webbasestarttask.dao;

import com.example.webbasestarttask.exception.DaoException;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
}
