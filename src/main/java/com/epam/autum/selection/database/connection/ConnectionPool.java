package com.epam.autum.selection.database.connection;

import com.epam.autum.selection.database.DatabaseProperties;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static Logger log = LogManager.getLogger(ConnectionPool.class);

    private static ReentrantLock lock = new ReentrantLock();
    private BlockingQueue<WrapperConnection> connections;

    private ConnectionPool() {

        DatabaseProperties properties = DatabaseProperties.getInstance();

        try {
            Class.forName(properties.getDriver());
        } catch (ClassNotFoundException e) {
            log.log(Level.FATAL, "Register Driver error:", e);
        }

        int poolSize = properties.getPoolSize();

        connections = new ArrayBlockingQueue<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            connections.add(new WrapperConnection(properties.getProperties(), properties.getURL()));
        }
        log.log(Level.DEBUG, "Connection pool initialized. Pool size = " + poolSize);

    }

    public static ConnectionPool getInstance() {
        return PoolHolder.pool;
    }

    /**
     * @return WrapperConnection wrapped in Optional if the pool is not empty, Optional.empty() otherwise
     */
    public Optional<WrapperConnection> takeConnection() {
        WrapperConnection connection = null;
        try {
            connection = connections.poll(5, TimeUnit.SECONDS);
            if (connection != null) {
                log.log(Level.DEBUG, "Connection taken. Pool size = " + connections.size());
            } else {
                log.log(Level.DEBUG, "Connection not taken. Pool size = " + connections.size());
            }
        } catch (InterruptedException e) {
            log.log(Level.ERROR, e);
        }
        return Optional.ofNullable(connection);
    }

    public boolean returnConnection(WrapperConnection connection) {
        boolean isReturned = false;
        isReturned = connections.add(connection);
        if (isReturned) {
            log.log(Level.DEBUG, "Connection returned. Pool size = " + connections.size());
        }
        return isReturned;
    }

    public void closePool() {
        while (!connections.isEmpty()) {
            WrapperConnection connection = connections.poll();
            connection.closeConnection();
        }

        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                log.log(Level.DEBUG, "Driver deregistered.", driver);
            } catch (SQLException e) {
                log.log(Level.ERROR, "Error while deregistering driver.", e);
            }
        }
        log.log(Level.DEBUG, "Connection pool closed.");
    }

    /**
     * Nested class for lazy pool initialization.
     */
    private static class PoolHolder {
        private static ConnectionPool pool;
        private static AtomicBoolean init = new AtomicBoolean(false);

        static {
            lock.lock();
            try {
                if (!init.get()) {
                    pool = new ConnectionPool();
                    init.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
