package com.example.webbasestarttask.service;

import com.example.webbasestarttask.exception.ServiceException;

public interface UserService {
    boolean authenticate(String login, String password) throws ServiceException;
}
