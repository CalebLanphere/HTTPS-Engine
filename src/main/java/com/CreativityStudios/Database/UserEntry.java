package com.CreativityStudios.Database;

import java.util.Base64;
import java.util.UUID;

public class UserEntry {
    private UUID id;
    private String email;
    private String password;
    private String domain;
    private UserPermissions permissions;

    public UserEntry(UUID id, String email, String password, String domain, UserPermissions permissions) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.domain = domain;
        this.permissions = permissions;
    }

    public UserEntry(UUID id, String email, String domain, UserPermissions permissions) {
        this.id = id;
        this.email = email;
        this.domain = domain;
        this.permissions = permissions;
    }

    public UserEntry(String email, String password, String domain, UserPermissions permissions) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = new String(Base64.getDecoder().decode(password));
        this.domain = domain;
        this.permissions = permissions;
    }

    public String getEmail() {
        return email;
    }

    public String getDomain() {
        return domain;
    }

    public String getEncryptedPassword() {
        return password;
    }

    public String getId() {
        return id.toString();
    }

    public UserPermissions getUserPermissions() {
        return permissions;
    }

    public String toString() {
        return "UserEntry" + new String("\nid: " + id.toString() + "\nemail: " + email + "\ndomain: " + domain + "\npermissions: " + permissions.toString()).indent(4);
    }
}
