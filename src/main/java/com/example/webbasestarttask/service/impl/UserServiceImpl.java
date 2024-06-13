package com.example.webbasestarttask.service.impl;

import com.example.webbasestarttask.dao.impl.UserDaoImpl;
import com.example.webbasestarttask.entity.User;
import com.example.webbasestarttask.exception.DaoException;
import com.example.webbasestarttask.exception.ServiceException;
import com.example.webbasestarttask.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    public void register(String username, String email, String password) throws ServiceException {
        User user = new User(username, email, password);
        if (userExists(email)){
            throw new ServiceException("User already exists");
        }
        try {
            userDao.insert(user);
            logger.info("Service register is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean authenticate(String email, String password) throws ServiceException {
        //validate login, pass + md5
        boolean match = false;
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
        return user !=null;
    }

    @Override
    public User findUserByEmail(String email) throws ServiceException {
        User user = null;
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
        boolean updated = false;
        user.setUserName(username);
        try {
            updated = userDao.update(user);
            logger.info("Service updateUsername is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return updated;
    }

    @Override
    public boolean deleteUser(String email) throws ServiceException {
        boolean deleted = false;
        try {
            deleted = userDao.delete(email);
            logger.info("Service deleteUser is succeed");
        } catch (DaoException e) {
            logger.error("Service: ", e.getMessage());
            throw new ServiceException(e);
        }
        return deleted;
    }
}