package com.example.webbasestarttask.dao;

import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.DaoException;

import java.util.List;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;
    User findUserByEmail(String email) throws DaoException;
    List<User> findAllByUserId(int id) throws DaoException;
}
