package com.epam.dao;

import com.epam.exception.DAOException;
import com.epam.model.periodical.Publisher;
import com.epam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.epam.util.JDBCUtils.printSQLException;

public class PublisherDao {

    private static final String INSERT_PUBLISHER_SQL = "INSERT INTO publisher" + "  (id, name) VALUES " + " (DEFAULT, ?);";
    private static final String SELECT_PUBLISHER_BY_ID = "SELECT id,name FROM publisher WHERE id =?;";
    private static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM publisher;";
    private static final String DELETE_PUBLISHERS_SQL = "DELETE FROM publisher where id = ?;";
    private static final String UPDATE_PUBLISHER_SQL = "UPDATE publisher SET name = ? where id = ?;";

    public PublisherDao() {
    }


    public void insert(Publisher publisher) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER_SQL)) {
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Publisher select(int id) {
        Publisher publisher = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                publisher = new Publisher(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return publisher;
    }

    public List<Publisher> selectAll() {
        List<Publisher> publishers = new CopyOnWriteArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PUBLISHERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                publishers.add(new Publisher(id, name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return publishers;
    }

    public boolean delete(int id) {
        boolean rowDeleted = false;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PUBLISHERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowDeleted;
    }

    public boolean update(Publisher publisher) {
        boolean rowUpdated = false;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISHER_SQL)) {
            statement.setInt(2, publisher.getId());
            statement.setString(1, publisher.getName());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    //clear all publishers for test only
    public void clear() {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM publisher")) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
