package com.epam.dao.impl;

import com.epam.dao.PublisherDao;
import com.epam.exception.ExistException;
import com.epam.model.periodical.Publisher;
import com.epam.util.JDBCUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class PublisherDaoImpl implements PublisherDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PublisherDaoImpl.class);

    private static final String INSERT_PUBLISHER_SQL = "INSERT INTO publisher" + "  (id, name) VALUES " + " (DEFAULT, ?);";
    private static final String SELECT_PUBLISHER_BY_ID = "SELECT id,name FROM publisher WHERE id =?;";
    private static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM publisher;";
    private static final String DELETE_PUBLISHERS_SQL = "DELETE FROM publisher where id = ?;";
    private static final String UPDATE_PUBLISHER_SQL = "UPDATE publisher SET name = ? where id = ?;";

    public PublisherDaoImpl() {
    }

    @Override
    public void insert(Publisher publisher) {
        LOGGER.info("INSERT PUBLISHER ID  {} NAME  {}", publisher.getId(), publisher.getName());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER_SQL)) {
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            try {
                throw new ExistException(publisher.getName());
            } catch (ExistException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
    }

    @Override
    public Publisher select(int id) {
        LOGGER.info("SELECT PUBLISHER WITH ID {}", id);
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
            LOGGER.error(e.getMessage(), e);
        }
        return publisher;
    }

    @Override
    public List<Publisher> selectAll() {
        LOGGER.info("SELECT ALL PUBLISHERS");
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
            LOGGER.error(e.getMessage(), e);
        }
        return publishers;
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("DELETE PUBLISHER WITH ID = {} ", id);
        boolean rowDeleted = false;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PUBLISHERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Publisher publisher) {
        LOGGER.info("UPDATE PUBLISHER WITH ID  = {} NAME = {} ", publisher.getId(), publisher.getName());
        boolean rowUpdated = false;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISHER_SQL)) {
            statement.setInt(2, publisher.getId());
            statement.setString(1, publisher.getName());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowUpdated;
    }

    //clear all publishers for test only
    @Override
    public void clear() {
        LOGGER.info("DELETE ALL PUBLISHERS");
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM publisher")) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
