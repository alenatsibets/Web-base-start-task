package com.example.webbasestarttask.controller;

import com.example.webbasestarttask.model.exception.ServiceException;
import com.example.webbasestarttask.model.pool.ConnectionPool;
import com.example.webbasestarttask.model.service.UserService;
import com.example.webbasestarttask.model.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/uploadAvatar")
@MultipartConfig
public class AvatarUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(AvatarUploadServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getSession().getAttribute("email").toString();
        UserService userService = UserServiceImpl.getInstance();
        byte[] imageBytes = null;
        try {
            imageBytes = userService.getImage(email);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        if (imageBytes != null) {
            response.setContentType("image/jpeg");
            OutputStream os = response.getOutputStream();
            os.write(imageBytes);
            os.flush();
            os.close();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 error
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getSession().getAttribute("email").toString();
        Part filePart = request.getPart("avatar");

        if (email == null || filePart == null || filePart.getSize() == 0) {
            response.getWriter().println("Invalid input.");
            return;
        }

        InputStream fileContent = filePart.getInputStream();

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false); // Disable auto-commit

            String sql = "UPDATE users SET avatar = ? WHERE email = ?";
            statement = connection.prepareStatement(sql);
            statement.setBinaryStream(1, fileContent, filePart.getSize());
            statement.setString(2, email);

            int rowsUpdated = statement.executeUpdate();
            connection.commit(); // Commit the transaction

            if (rowsUpdated > 0) {
                response.sendRedirect(request.getHeader("referer"));
                LOGGER.log(Level.INFO, "Avatar uploaded successfully for email: {0}", email);
            } else {
                response.getWriter().println("No user found with the provided email.");
                LOGGER.log(Level.WARNING, "No user found with email: {0}", email);
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback the transaction in case of error
                } catch (SQLException ex) {
                    throw new ServletException("Error rolling back transaction", ex);
                }
            }
            LOGGER.log(Level.SEVERE, "Database connection problem.", e);
            throw new ServletException("Database connection problem.", e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Re-enable auto-commit
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}