package com.example.webbasestarttask.model.service.impl;

import com.example.webbasestarttask.model.dao.impl.UserDaoImpl;
import com.example.webbasestarttask.model.entity.User;
import com.example.webbasestarttask.model.exception.DaoException;
import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.pool.ConnectionPool;
import com.example.webbasestarttask.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private static UserServiceImpl instance = new UserServiceImpl();
    private final UserDaoImpl userDao = UserDaoImpl.getInstance();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean register(String username, String email, String password, boolean isVerified) throws ServiceException {
        boolean inserted;
        User user = new User(username, email, password, isVerified);
        if (userExists(email)) {
            throw new ServiceException("User already exists");
        }
        try {
            inserted = userDao.insert(user);
            logger.info("Service register is succeed: ", inserted);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return inserted;
    }

    @Override
    public String verification(String email) throws ServiceException {
        UserDaoImpl userDao = UserDaoImpl.getInstance();
        String verificationCode;
        try {
            verificationCode = userDao.verification(email);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return verificationCode;
    }

    @Override
    public boolean authenticate(String email, String password) throws ServiceException {
        boolean match;
        try {
            match = userDao.authenticate(email, password);
            logger.info("Service authenticate is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean userExists(String email) throws ServiceException {
        User user;
        try {
            user = userDao.findUserByEmail(email);
            logger.info("Service userExists is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return user != null;
    }

    @Override
    public User findUserByEmail(String email) throws ServiceException {
        User user;
        try {
            user = userDao.findUserByEmail(email);
            logger.info("Service findUserByEmail is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return user;
    }

    @Override
    public boolean updateUsername(User user, String username) throws ServiceException {
        boolean updated;
        user.setUserName(username);
        try {
            updated = userDao.update(user);
            logger.info("Service updateUsername is succeed: ", updated);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return updated;
    }

    @Override
    public boolean updateStatus(boolean isVerified, String email) throws ServiceException {
        boolean updated;
        try {
            updated = userDao.updateStatus(isVerified, email);
            logger.info("Service updateStatus is succeed: ", updated);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return updated;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String emaiL) throws ServiceException {
        boolean match;
        try {
            match = userDao.changePassword(oldPassword, newPassword, emaiL);
        } catch (DaoException e) {
            logger.error("error while changing password");
            throw new ServiceException(e);
        }
        return match;
    }

    @Override
    public boolean uploadFile(String pathOfFile, String email) throws ServiceException {
        try {
            return userDao.uploadFile(pathOfFile, email);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUser(String email) throws ServiceException {
        boolean deleted;
        try {
            deleted = userDao.delete(email);
            logger.info("Service deleteUser is succeed: ", deleted);
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return deleted;
    }

    @Override
    public byte[] getImage(String email) throws ServiceException {
        byte[] imageBytes = null;
        try {
            imageBytes = userDao.getImage(email);
        } catch (Exception e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return imageBytes;
    }
}