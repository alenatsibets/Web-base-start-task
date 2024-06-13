package com.example.webbasestarttask.dao.impl;

import com.example.webbasestarttask.dao.BaseDao;
import com.example.webbasestarttask.dao.ContactDao;
import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.exception.DaoException;
import com.example.webbasestarttask.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDaoImpl extends BaseDao<Contact> implements ContactDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_CONTACT = "INSERT INTO contacts (name, number, userId) VALUES (?, ?, ?);";
    private static final String DELETE_CONTACT = "DELETE FROM contacts WHERE id = ?;";
    private static final String UPDATE_CONTACT = "UPDATE contacts SET name = ?, number = ? WHERE id = ?;";
    private static final String UPDATE_CONTACT_NAME = "UPDATE contacts SET name = ? WHERE id = ?;";
    private static final String UPDATE_CONTACT_NUMBER = "UPDATE contacts SET number = ? WHERE id = ?;";
    private static final String SELECT_CONTACT_BY_NAME = "SELECT id, name, number, userId FROM contacts WHERE name = ?;";
    private static final String SELECT_ALL_CONTACTS = "SELECT name, number FROM contacts";
    private static final String SELECT_ALL_CONTACTS_BY_USERID = "SELECT name, number FROM contacts WHERE userId = ?";
    private static ContactDaoImpl instance = new ContactDaoImpl();
    private ContactDaoImpl() {
    }
    public static ContactDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(Contact contact) throws DaoException {
        boolean inserted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CONTACT)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhoneNumber());
            statement.setInt(3, contact.getUserId());
            statement.executeUpdate();
            inserted = true;
            logger.info("DAO insert is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return inserted;    }

    @Override
    public boolean delete(Contact contact) throws DaoException {
        boolean deleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_CONTACT)) {
            statement.setInt(1, contact.getId());
            deleted = statement.executeUpdate() > 0;
            logger.info("DAO delete is succeed:" + deleted);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return deleted;
    }

    @Override
    public List<Contact> findAll() throws DaoException {
        List<Contact> contacts = new ArrayList<>();
        Contact contact;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CONTACTS);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                int userId = resultSet.getInt("userId");
                contact = new Contact(id, userId, phoneNumber, name);
                contacts.add(contact);
            }
            logger.info("DAO select all is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return contacts;
    }

    public List<Contact> findAllByUserId(int userId) throws DaoException {
        List<Contact> contacts = new ArrayList<>();
        Contact contact;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CONTACTS_BY_USERID);) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String phoneNumber = resultSet.getString("phoneNumber");
                contact = new Contact(id, userId, phoneNumber, name);
                contacts.add(contact);
            }
            logger.info("DAO select all by UserId is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return contacts;
    }

    public Contact findContactByName(String name) throws DaoException {
        Contact contact = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_CONTACT_BY_NAME)) {
            statement.setString(1, name);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String number = rs.getString("phoneNumber");
                contact = new Contact(id, userId, number, name);
            }
            logger.info("SELECT contact BY NAME successful");
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException(e);
        }
        return contact;
    }

    @Override
    public boolean update(Contact contact) throws DaoException {
        boolean updated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACT)) {
            statement.setString(1, contact.getName());
            statement.setString(2, contact.getPhoneNumber());
            statement.setInt(3, contact.getId());
            updated = statement.executeUpdate() > 0;
            logger.info("DAO update is succeed: " + updated);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return updated;
    }
    public boolean updateContactName(String name, int id) {
        boolean updated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACT_NAME)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            updated = statement.executeUpdate() > 0;
            logger.info("DAO name update is succeed: " + updated);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return updated;
    }

    public boolean updateContactNumber(String number, int id) {
        boolean updated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CONTACT_NUMBER)) {
            statement.setString(1, number);
            statement.setInt(2, id);
            updated = statement.executeUpdate() > 0;
            logger.info("DAO number update is succeed:" + updated);
        } catch (SQLException e){
            logger.error(e.getMessage());
        }
        return updated;
    }
}
