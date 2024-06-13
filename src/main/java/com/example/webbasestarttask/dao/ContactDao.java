package com.example.webbasestarttask.dao;

import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.exception.DaoException;

public interface ContactDao {
    Contact findContactByName(String name) throws DaoException;
}
