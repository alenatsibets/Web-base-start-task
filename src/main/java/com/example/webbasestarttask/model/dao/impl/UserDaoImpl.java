package com.example.webbasestarttask.model.dao.impl;

import com.example.webbasestarttask.model.dao.BaseDao;
import com.example.webbasestarttask.model.dao.UserDao;
import com.example.webbasestarttask.model.entity.User;
import com.example.webbasestarttask.model.exception.DaoException;
import com.example.webbasestarttask.model.pool.ConnectionPool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.webbasestarttask.util.CodeGenerator;
import com.example.webbasestarttask.util.EmailSender;
import org.mindrot.jbcrypt.BCrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;


public class UserDaoImpl extends BaseDao<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    public static final String INSERT_USER = "INSERT INTO users (user_name, email, password, is_verified) VALUES (?, ?, ?, ?);";
    private static final String DELETE_USER = "DELETE FROM users WHERE email = ?;";
    private static final String UPDATE_USER = "UPDATE users SET user_name = ?, email = ? WHERE user_id = ?;";
    private static final String UPDATE_STATUS = "UPDATE users SET is_verified = ? WHERE email = ?;";
    private static final String SELECT_USER_BY_EMAIL = "SELECT user_id, user_name, email, password, is_verified FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT user_id, user_name, email, password, is_verified FROM users";
    private static final String SELECT_ALL_USERS_BY_ID = "SELECT user_id, user_name, email, password, is_verified FROM users WHERE user_id = ?";
    public static final String SELECT_LOGIN_PASSWORD = "SELECT password FROM users WHERE email = ?";
    public static final String CHANGE_PASSWORD = "UPDATE users SET password = ? WHERE email = ?";
    public static final String UPLOAD_FILE = "UPDATE users SET avatar = ? WHERE email = ?";
    public static final String SELECT_FILE = "SELECT avatar FROM users WHERE email = ?";
    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean insert(User user) throws DaoException {
        boolean inserted = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            statement.setString(3, hashedPassword);
            statement.setBoolean(4, user.isVerified());
            statement.executeUpdate();
            inserted = true;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO insert is succeed: ");
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

    @Override
    public boolean delete(String email) throws DaoException {
        boolean deleted = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_USER);
            statement.setString(1, email);
            deleted = statement.executeUpdate() > 0;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
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
                boolean isVerified = resultSet.getBoolean("is_verified");
                user = new User(id, username, email, password, isVerified);
                users.add(user);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO select all is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public User findUserByEmail(String email) throws DaoException {
        User user = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("user_id");
                String username = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                boolean isVerified = resultSet.getBoolean("is_verified");
                user = new User(id, username, email, password, isVerified);
            }
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO select by email is succeed");
        } catch (SQLException e) {
            logger.error("DAO find: ", e.getMessage());
            throw new DaoException(e);
        }
        return user;
    }

    @Override
    public List<User> findAllByUserId(int id) throws DaoException {
        List<User> users = new ArrayList<>();
        User user;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean isVerified = resultSet.getBoolean("is_verified");
                user = new User(id, username, email, password, isVerified);
                users.add(user);
            }
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO select all is succeed");
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public boolean update(User user) throws DaoException {
        boolean updated;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setInt(3, user.getId());
            updated = statement.executeUpdate() > 0;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO update is succeed: " + updated);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return updated;
    }

    @Override
    public boolean updateStatus(boolean isVerified, String email) throws DaoException {
        boolean updated;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS);
            statement.setBoolean(1, isVerified);
            statement.setString(2, email);
            updated = statement.executeUpdate() > 0;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO updateStatus is succeed: " + updated);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return updated;
    }

    @Override
    public boolean authenticate(String login, String password) throws DaoException {
        boolean match = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_LOGIN_PASSWORD);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            String passFromDb;
            if (resultSet.next()) {
                passFromDb = resultSet.getString(1);
                match = BCrypt.checkpw(password, passFromDb);
            }
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("DAO authenticate is succeed: " + match);
        } catch (SQLException e) {
            logger.error("DAO: ", e.getMessage());
            throw new DaoException(e);
        }
        return match;
    }

    @Override
    public String verification(String email) throws DaoException {
        EmailSender sendEmail = EmailSender.getInstance();
        String verificationCode = CodeGenerator.generate();
        sendEmail.sendEmail(email, verificationCode);
        return verificationCode;
    }

    @Override
    public boolean changePassword(String oldPassword, String newPassword, String email) throws DaoException {
        boolean updated = false;
        if (!authenticate(email, oldPassword)) {
            throw new DaoException();
        }
        if (oldPassword.isEmpty() || newPassword.isEmpty() || email.isEmpty()) {
            throw new DaoException();
        } else {
            try {
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(CHANGE_PASSWORD);
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                statement.setString(1, hashedPassword);
                statement.setString(2, email);
                int rowsAffected = statement.executeUpdate();
                statement.close();
                ConnectionPool.getInstance().releaseConnection(connection);
                updated = rowsAffected > 0;
                logger.info("DAO changePassword is succeed: " + updated);
            } catch (SQLException e) {
                logger.error("DAO: ", e.getMessage());
                throw new DaoException(e);
            }
            return updated;
        }
    }

    @Override
    public boolean uploadFile(String pathName, String email) throws DaoException {
        boolean updated = false;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPLOAD_FILE);
            statement.setString(1, pathName);
            statement.setString(2, email);
            updated = statement.executeUpdate() > 0;
            statement.close();
            ConnectionPool.getInstance().releaseConnection(connection);
            logger.info("Upload image successful= " + updated);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DaoException("Error uploading image");
        }
        return updated;
    }
    private byte[] resizeImage(byte[] imageBytes, int width, int height) throws DaoException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(bais);

        } catch (IOException e) {
            throw new DaoException(e);
        }

        Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage bufferedResizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedResizedImage.createGraphics();
        g2d.drawImage(resizedImage, 0, 0, null);
        g2d.dispose();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedResizedImage, "jpg", baos);
        } catch (IOException e) {
            throw new DaoException(e);
        }
        return baos.toByteArray();
    }

    public byte[] getImage(String email) throws DaoException {
        byte[] imageBytes = null;
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(SELECT_FILE);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                imageBytes = rs.getBytes("avatar");
            }
            imageBytes = resizeImage(imageBytes, 200, 200);

            rs.close();
            stmt.close();
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DaoException("Error getting image");
        }
        return imageBytes;
    }
}