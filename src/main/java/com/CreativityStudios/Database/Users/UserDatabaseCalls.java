package com.CreativityStudios.Database.Users;

import com.CreativityStudios.Database.DatabaseConnector;
import com.CreativityStudios.Database.UserEntry;
import com.CreativityStudios.JSON.JSONReader;
import com.CreativityStudios.JSON.JSONWriter;
import jakarta.json.JsonObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDatabaseCalls {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static ArrayList<UserEntry> getAllUserEntriesFromUsersWithUserEmailAndPassword(String email, String password) throws SQLException {
        PreparedStatement getUserFromDBStatement = DatabaseConnector.getUserDbReaderConnection().prepareStatement("SELECT userId, userEmail, userGroup, userPermissions FROM users WHERE userEmail=? && userPassword=?");
        getUserFromDBStatement.setString(1, email);
        getUserFromDBStatement.setString(2, password);

        return parseResultSetAsUserEntryArrayList(getUserFromDBStatement.executeQuery());
    }

    public static boolean ifUserEmailFromUsersWithUserEmailExists(String email) throws SQLException {
        PreparedStatement getUserFromDBStatement = DatabaseConnector.getUserDbReaderConnection().prepareStatement("SELECT userEmail FROM users WHERE userEmail=?");
        getUserFromDBStatement.setString(1, email);

        return parseResultSetAsBoolean(getUserFromDBStatement.executeQuery());
    }

    public static boolean insertUserEntryIntoUsers(UserEntry entry) throws SQLException {
        PreparedStatement getUserFromDBStatement = DatabaseConnector.getUserDbWriterConnection().prepareStatement
                ("INSERT INTO users VALUES(?, ?, ?, ?, ?)");
        getUserFromDBStatement.setString(1, entry.getId());
        getUserFromDBStatement.setString(2, entry.getEmail());
        getUserFromDBStatement.setString(3, entry.getEncryptedPassword());
        getUserFromDBStatement.setString(4, entry.getDomain());
        getUserFromDBStatement.setString(5, JSONReader.parseJsonObjectAsString(JSONWriter.createUserPermissionsJsonObject(entry.getUserPermissions())));
        return getUserFromDBStatement.execute();
    }

    private static ArrayList<UserEntry> parseResultSetAsUserEntryArrayList(ResultSet resultFromQuery) throws SQLException {
        ArrayList<UserEntry> parsedUsers = new ArrayList<>();

        while(resultFromQuery.next()) {
            parsedUsers.add(new UserEntry(
                        UUID.fromString(resultFromQuery.getString("userId")),
                        resultFromQuery.getString("userEmail"),
                        resultFromQuery.getString("userGroup"),
                        JSONReader.parseJsonObjectAsUserPermissions(JSONReader.jsonStringToJsonObject(resultFromQuery.getString("userPermissions")))
                    )
            );

        }

        return parsedUsers;
    }

    private static Boolean parseResultSetAsBoolean(ResultSet resultFromQuery) throws SQLException {
        ArrayList<String> emails = new ArrayList<>();

        while(resultFromQuery.next()) {
            emails.add(resultFromQuery.getString("userEmail"));
        }

        return !emails.isEmpty();
    }
}
