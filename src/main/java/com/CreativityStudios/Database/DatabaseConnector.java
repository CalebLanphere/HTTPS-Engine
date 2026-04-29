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

import com.CreativityStudios.File.FileReader;
import com.CreativityStudios.JSON.JSONReader;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnector {
    private static final BasicDataSource POOLED_READER_CONNECTIONS = new BasicDataSource();
    private static final BasicDataSource POOLED_WRITER_CONNECTIONS = new BasicDataSource();
    private static final BasicDataSource POOLED_USERDB_READER_CONNECTIONS = new BasicDataSource();
    private static final BasicDataSource POOLED_USERDB_WRITER_CONNECTIONS = new BasicDataSource();
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // TODO make properly static by removing constructor and forcing it to construct under usage
    public DatabaseConnector() throws IOException {
        FileReader reader = new FileReader("src/main/resources/Database/.dbaccess.json");
        DatabaseCredentials[] credentials = JSONReader.parseJsonObjectAsDatabaseCredentials(JSONReader.jsonStringToJsonObject(reader.readFileToString()));

        for (DatabaseCredentials credential : credentials) {
            switch (credential.getCredentialType()) {
                case "Reader":
                    POOLED_READER_CONNECTIONS.setInitialSize(10);
                    POOLED_READER_CONNECTIONS.setTestOnCreate(true);
                    POOLED_READER_CONNECTIONS.setTestWhileIdle(true);
                    POOLED_READER_CONNECTIONS.setRemoveAbandonedTimeout(Duration.ofMinutes(2));
                    POOLED_READER_CONNECTIONS.setUrl(credential.getDatabaseUrl());
                    POOLED_READER_CONNECTIONS.setUsername(credential.getUsername());
                    POOLED_READER_CONNECTIONS.setPassword(credential.getPassword());
                    break;
                case "Writer":
                    POOLED_WRITER_CONNECTIONS.setInitialSize(2);
                    POOLED_WRITER_CONNECTIONS.setTestOnCreate(true);
                    POOLED_WRITER_CONNECTIONS.setTestWhileIdle(true);
                    POOLED_WRITER_CONNECTIONS.setRemoveAbandonedTimeout(Duration.ofMinutes(2));
                    POOLED_WRITER_CONNECTIONS.setUrl(credential.getDatabaseUrl());
                    POOLED_WRITER_CONNECTIONS.setUsername(credential.getUsername());
                    POOLED_WRITER_CONNECTIONS.setPassword(credential.getPassword());
                    break;
                case "UserDBReader":
                    POOLED_USERDB_READER_CONNECTIONS.setInitialSize(10);
                    POOLED_USERDB_READER_CONNECTIONS.setTestOnCreate(true);
                    POOLED_USERDB_READER_CONNECTIONS.setTestWhileIdle(true);
                    POOLED_USERDB_READER_CONNECTIONS.setRemoveAbandonedTimeout(Duration.ofMinutes(2));
                    POOLED_USERDB_READER_CONNECTIONS.setUrl(credential.getDatabaseUrl());
                    POOLED_USERDB_READER_CONNECTIONS.setUsername(credential.getUsername());
                    POOLED_USERDB_READER_CONNECTIONS.setPassword(credential.getPassword());
                    break;
                case "UserDBWriter":
                    POOLED_USERDB_WRITER_CONNECTIONS.setInitialSize(2);
                    POOLED_USERDB_WRITER_CONNECTIONS.setTestOnCreate(true);
                    POOLED_USERDB_WRITER_CONNECTIONS.setTestWhileIdle(true);
                    POOLED_USERDB_WRITER_CONNECTIONS.setRemoveAbandonedTimeout(Duration.ofMinutes(2));
                    POOLED_USERDB_WRITER_CONNECTIONS.setUrl(credential.getDatabaseUrl());
                    POOLED_USERDB_WRITER_CONNECTIONS.setUsername(credential.getUsername());
                    POOLED_USERDB_WRITER_CONNECTIONS.setPassword(credential.getPassword());
                    break;
            }
        }
    }

    /**
     * Gets a random free connection to use for database reading communications
     *
     * @return Connection that is used for database communications
     * @throws SQLException If the connection to the database if invalid
     */
    public static Connection getReaderConnection() throws SQLException {
        return POOLED_READER_CONNECTIONS.getConnection();
    }

    /**
     * Gets a random free connection to use for database writing communications
     *
     * @return Connection that is used for database communications
     * @throws SQLException If the connection to the database if invalid
     */
    public static Connection getWriterConnection() throws SQLException {
        return POOLED_WRITER_CONNECTIONS.getConnection();
    }

    /**
     * Gets a random free connection to use for account reading communications
     *
     * @return Connection that is used for database communications
     * @throws SQLException If the connection to the database if invalid
     */
    public static Connection getUserDbReaderConnection() throws SQLException {
        return POOLED_USERDB_READER_CONNECTIONS.getConnection();
    }

    /**
     * Gets a random free connection to use for account writing communications
     *
     * @return Connection that is used for database communications
     * @throws SQLException If the connection to the database if invalid
     */
    public static Connection getUserDbWriterConnection() throws SQLException {
        return POOLED_USERDB_WRITER_CONNECTIONS.getConnection();
    }
}
