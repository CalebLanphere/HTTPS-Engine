/**
 * DatabaseConnector class
 *
 * Creates and handles database connections
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.Database;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;

public class DatabaseConnector {
    private static final BasicDataSource POOLED_CONNECTIONS = new BasicDataSource();

    static {
        POOLED_CONNECTIONS.setInitialSize(10);
        POOLED_CONNECTIONS.setTestOnCreate(true);
        POOLED_CONNECTIONS.setTestWhileIdle(true);
        POOLED_CONNECTIONS.setRemoveAbandonedTimeout(Duration.ofMinutes(2));
//        POOLED_CONNECTIONS.setUrl();
//        POOLED_CONNECTIONS.setUsername();
//        POOLED_CONNECTIONS.setPassword();
    }

    /**
     * Gets a random free connection to use for database communications
     * @return Connection that is used for database communications
     * @throws SQLException If the connection to the database if invalid
     */
    public static Connection getConnection() throws SQLException {
        return POOLED_CONNECTIONS.getConnection();
    }
}
