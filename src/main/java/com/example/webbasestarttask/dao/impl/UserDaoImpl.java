package com.example.webbasestarttask.dao.impl;

import com.example.webbasestarttask.dao.BaseDao;
import com.example.webbasestarttask.dao.UserDao;
import com.example.webbasestarttask.entity.Contact;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.DaoException;
import com.example.webbasestarttask.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_USER = "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM users WHERE email = ?;";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ? WHERE id = ?;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT id, username, email, password FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT id, username, email, password FROM users";
    private static final String SELECT_ALL_USERS_BY_ID = "SELECT id, username, email, password FROM users WHERE id = ?";
    private static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE lastname = ?";
    private static UserDaoImpl instance = new UserDaoImpl();
    private UserDaoImpl() {
    }
    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        boolean inserted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            statement.setString(3, hashedPassword);
            statement.executeUpdate();
            inserted = true;
            logger.info("DAO insert is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return inserted;
    }

    @Override
    public boolean delete(User user) throws DaoException {
        String email = user.getEmail();
        return this.delete(email);
    }
    public boolean delete(String email) throws DaoException {
        boolean deleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER)) {
            statement.setString(1, email);
            deleted = statement.executeUpdate() > 0;
            logger.info("DAO delete is succeed:" + deleted);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return deleted;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new User(id, username, email, password);
                users.add(user);
            }
            logger.info("DAO select all is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    public User findUserByEmail(String email) throws DaoException {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                user = new User(id, username, email, password);
            }
            logger.info("DAO select by email is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> findAllByUserId(int id) throws DaoException {
        List<User> users = new ArrayList<>();
        User user;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_BY_ID);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                user = new User(id, username, email, password);
                users.add(user);
            }
            logger.info("DAO select all is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean updated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            updated = statement.executeUpdate() > 0;
            logger.info("DAO update is succeed: " + updated);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return updated;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                match = BCrypt.checkpw(password, passFromDb);
            }
            logger.info("DAO authenticate is succeed: " + match);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return match;
    }
}