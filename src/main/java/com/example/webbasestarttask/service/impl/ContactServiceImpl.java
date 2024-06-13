package com.example.webbasestarttask.service.impl;

import com.example.webbasestarttask.dao.impl.ContactDaoImpl;
import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.exception.DaoException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.ContactService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ContactServiceImpl implements ContactService {
    private static final Logger logger = LogManager.getLogger();
    private static ContactServiceImpl instance = new ContactServiceImpl();
    private final ContactDaoImpl contactDao = ContactDaoImpl.getInstance();

    private ContactServiceImpl() {
    }

    public static ContactServiceImpl getInstance() {
        return instance;
    }
    @Override
    public List<Contact> getContacts(int userId) throws ServiceException {
        List<Contact> contacts;
        try {
            contacts = contactDao.findAll();
            logger.info("Service getContacts is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return contacts;
    }

    @Override
    public void createContact(String name, String number, int userId) throws ServiceException {
        Contact contact = new Contact(userId, name, number);
        if (contactExists(name)){
            throw new ServiceException("Contact already exists");
        }
        try {
            contactDao.insert(contact);
            logger.info("Service createContact is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateContact(Contact contact) throws ServiceException {
        boolean updated = false;
        try {
            updated = contactDao.update(contact);
            logger.info("Service updateContact is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return updated;
    }
    public boolean updateContactName(String name, String newName) throws ServiceException {
        Contact contact;
        try {
            contact = contactDao.findContactByName(name);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        boolean nameUpdated = contactDao.updateContactName(newName, contact.getId());
        return nameUpdated;
    }

    public boolean updateContactNumber(String name, String newNumber) throws ServiceException {
        Contact contact;
        try {
            contact = contactDao.findContactByName(name);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        boolean numberUpdated = contactDao.updateContactNumber(newNumber, contact.getId());
        return numberUpdated;
    }

    @Override
    public boolean deleteContact(Contact contact) throws ServiceException {
        boolean deleted = false;
        try {
            deleted = contactDao.delete(contact);
            logger.info("Service deleteContact is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return deleted;
    }

    @Override
    public boolean contactExists(String name) throws ServiceException {
        Contact contact;
        try {
            contact = contactDao.findContactByName(name);
            logger.info("Service contactExists is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return contact !=null;
    }

    @Override
    public Contact findContactByName(String name) throws ServiceException {
        Contact contact;
        try {
            contact = contactDao.findContactByName(name);
            logger.info("Service findContactByName is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return contact;
    }
}
