package com.epam.dao;

import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.periodical.Publisher;
import com.epam.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.epam.util.JDBCUtils.printSQLException;

public class PeriodicalsCategoryDao {
    private static final String INSERT_PERIODICAL_CATEGORY_SQL = "INSERT INTO periodical_category" + "  (id, name) VALUES " + " (DEFAULT, ?);";
    private static final String SELECT_PERIODICAL_CATEGORY_BY_ID = "SELECT id,name FROM periodical_category WHERE id =?;";
    private static final String SELECT_ALL_PERIODICAL_CATEGORY = "SELECT * FROM periodical_category;";
    private static final String DELETE_PERIODICAL_CATEGORY_SQL = "DELETE FROM periodical_category where id = ?;";
    private static final String UPDATE_PERIODICAL_CATEGORY_SQL = "UPDATE periodical_category SET name = ? where id = ?;";

    public PeriodicalsCategoryDao() {
    }

    public void insertPublisher(PeriodicalCategory periodicalCategory) throws SQLException {
        System.out.println(INSERT_PERIODICAL_CATEGORY_SQL);
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERIODICAL_CATEGORY_SQL)) {
            preparedStatement.setString(1, periodicalCategory.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Publisher selectPublisher(int id) {
        Publisher publisher = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERIODICAL_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("id");
                String email = rs.getString("name");
                publisher = new Publisher(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return publisher;
    }

    public List<Publisher> selectAllPublishers() {
        List<Publisher> publishers = new CopyOnWriteArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERIODICAL_CATEGORY)) {
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

    public boolean deletePublisher(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERIODICAL_CATEGORY_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePublisher(Publisher publisher) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL_CATEGORY_SQL)) {
            statement.setInt(1, publisher.getId());
            statement.setString(2, publisher.getName());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    //clear all periodical categories
    public void clear() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM periodical_category")) {
            statement.execute();
        }
    }
}
