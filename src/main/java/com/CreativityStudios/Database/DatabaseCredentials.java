package com.CreativityStudios.Database;

public class DatabaseCredentials {
    private String databaseUrl;
    private String username;
    private String password;
    private String credentialType;

    public DatabaseCredentials(String databaseUrl, String username, String password, String credentialType) {
        this.databaseUrl = databaseUrl;
        this.username = username;
        this.password = password;
        this.credentialType = credentialType;
    }

    public DatabaseCredentials() {}

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public void setDatabaseUsername(String username) {
        this.username = username;
    }

    public void setDatabasePassword(String password) {
        this.password = password;
    }

    public void setDatabaseCredentialType(String credentialType) {
        this.credentialType = credentialType;
    }

    public String getCredentialType() {
        return credentialType;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    @Override
    public String toString() {
        return "URL: " + getDatabaseUrl() + " | USERNAME: " + getUsername();
    }
}
