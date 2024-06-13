package com.example.webbasestarttask.service;

import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.ServiceException;

public interface UserService {
    void register(String username, String email, String password) throws ServiceException;
    boolean authenticate(String email, String password) throws ServiceException;
    boolean userExists(String email) throws ServiceException;
    User findUserByEmail(String email) throws ServiceException;
    boolean updateUsername(User user, String username) throws ServiceException;
    boolean deleteUser(String email) throws ServiceException;
}