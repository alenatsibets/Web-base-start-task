package com.example.webbasestarttask.service;

import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.exception.ServiceException;

import java.util.List;

public interface ContactService {
    List<Contact> getContacts(int userId) throws ServiceException;
    void createContact(String name, String number, int userId) throws ServiceException;
    boolean updateContact(Contact contact) throws ServiceException;
    boolean updateContactName(String name, String newName) throws ServiceException;
    boolean updateContactNumber(String name, String newNumber) throws ServiceException;
    boolean deleteContact(Contact contact) throws ServiceException;
    boolean contactExists(String name) throws ServiceException;
    Contact findContactByName(String name) throws ServiceException;
}
