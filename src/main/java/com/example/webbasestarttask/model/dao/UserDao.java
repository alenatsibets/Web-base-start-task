package com.example.webbasestarttask.model.dao;

import com.example.webbasestarttask.model.entity.User;
import com.example.webbasestarttask.model.exception.DaoException;

import java.io.InputStream;
import java.util.List;

public interface UserDao {
    boolean authenticate(String login, String password) throws DaoException;

    String verification(String email) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    boolean updateStatus(boolean isVerified, String email) throws DaoException;

    List<User> findAllByUserId(int id) throws DaoException;

    boolean delete(String email) throws DaoException;

    boolean changePassword(String oldPassword, String newPassword, String email) throws DaoException;

    boolean uploadFile(String pathName, String email) throws DaoException;
    byte[] getImage(String email) throws DaoException;
}
