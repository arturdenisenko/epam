package com.epam.model.user;

import java.util.UUID;

public class User {
    private final String uuid;
    private String fullName;
    private UserType userType;

    public User() {
        this(UUID.randomUUID().toString());
    }

    public User(String uuid) {
        this.uuid = uuid;
    }
}
