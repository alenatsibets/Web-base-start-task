package com.example.webbasestarttask.model.service;

import com.example.webbasestarttask.model.entity.User;
import com.example.webbasestarttask.model.exception.ServiceException;

import java.io.InputStream;

public interface UserService {
    boolean register(String username, String email, String password, boolean isVerified) throws ServiceException;

    String verification(String email) throws ServiceException;

    boolean authenticate(String email, String password) throws ServiceException;

    boolean userExists(String email) throws ServiceException;

    User findUserByEmail(String email) throws ServiceException;

    boolean updateUsername(User user, String username) throws ServiceException;

    boolean updateStatus(boolean isVerified, String email) throws ServiceException;

    boolean deleteUser(String email) throws ServiceException;

    boolean changePassword(String oldPassword, String newPassword, String username) throws ServiceException;

    boolean uploadFile(String pathOfFile, String email) throws ServiceException;

    byte[] getImage(String email) throws ServiceException;
}