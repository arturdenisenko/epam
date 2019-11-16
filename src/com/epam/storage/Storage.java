package com.epam.storage;

import com.epam.model.user.User;

import java.util.List;

public interface Storage {

    void clear();

    void update(User user);

    void save(User user);

    User get(String uuid);

    void delete(String uuid);

    List<User> getAllSorted();

    int size();
}
