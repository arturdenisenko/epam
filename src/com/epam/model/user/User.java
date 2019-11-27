package com.epam.model.user;

import java.util.UUID;

public class User {
    private final String uuid;
    private String fullName;
    private String email;
    private String address;
    private String password;
    private UserType userType;

    public User() {
        this(UUID.randomUUID().toString());
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public User(String uuid) {
        this.uuid = uuid;
    }
}
