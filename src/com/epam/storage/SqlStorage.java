package com.epam.storage;

import com.epam.model.user.User;
import com.epam.sql.SqlHelper;

import java.sql.DriverManager;
import java.util.List;

public class SqlStorage implements Storage {
    //ConnectionFactory connectionFactory;
    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }
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
