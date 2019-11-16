package com.epam.storage;

import com.epam.model.user.User;
import com.epam.sql.ConnectionFactory;

import java.util.List;

public class SqlStorage implements Storage {
    //ConnectionFactory connectionFactory;

    @Override
    public void clear() {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void save(User user) {

    }

    @Override
    public User get(String uuid) {
        return null;
    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public List<User> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
