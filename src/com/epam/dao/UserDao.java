package com.epam.dao;

import com.epam.model.user.User;
import com.epam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.epam.util.JDBCUtils.printSQLException;

public class UserDao {
    //TODO userDAO
    private static final String INSERT_USER_SQL = "INSERT INTO user" + "  (id, name, password, email, address, user_type_fk) VALUES " + " (DEFAULT, ?,?,?,?,?);";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE id =?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM publisher;";
    private static final String DELETE_USER_SQL = "DELETE FROM publisher where id = ?;";
    private static final String UPDATE_USER_SQL = "UPDATE publisher SET name = ? where id = ?;";

    public UserDao() {
    }


    public void insert(User user) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }


}
