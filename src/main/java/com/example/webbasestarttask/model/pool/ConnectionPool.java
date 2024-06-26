package com.example.webbasestarttask.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private static ConnectionPool instance;
    private BlockingDeque<Connection> free = new LinkedBlockingDeque<>(CONNECTION_POOL_CAPACITY);
    private BlockingDeque<Connection> used = new LinkedBlockingDeque<>(CONNECTION_POOL_CAPACITY);
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);
    static Logger logger = LogManager.getLogger();
    private static final int CONNECTION_POOL_CAPACITY = 8;
    private static final String POSTGRES_CONNECTION = "jdbc:postgresql://localhost:5434/Users";

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            logger.error("cannot register driver");
            throw new ExceptionInInitializerError(e.getMessage());
        }
    }

    private ConnectionPool() {
        Properties prop = getProperties();
        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(POSTGRES_CONNECTION, prop);
            } catch (SQLException e) {
                logger.error("cannot get connection");
                throw new ExceptionInInitializerError(e);
            }
            free.add(connection);
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            used.remove(connection);
            free.put(connection);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void destroyPool() {
        for (int i = 0; i < CONNECTION_POOL_CAPACITY; i++) {
            try {
                free.take().close();
            } catch (SQLException | InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info(String.format("unregistering jdbc driver: %s", driver));
            } catch (SQLException e) {
                logger.error(String.format("Error unregistering driver %s", driver), e);
            }
        }
    }

    private static Properties getProperties() {
        Properties prop = new Properties();
        prop.put("user", "postgres");
        prop.put("password", "lFwu38wjw7s02s");
        prop.put("autoReconnect", "true");
        prop.put("characterEncoding", "UTF-8");
        prop.put("useUnicode", "true");
        prop.put("useSSL", "true");
        prop.put("useJDBCCompliant Timezone Shift", "true");
        prop.put("useLegacyDatetimeCode", "false");
        prop.put("serverTimezone", "UTC");
        prop.put("serverSslCert", "classpath: server.crt");
        return prop;
    }
}