package com.epam.dao.impl;

import com.epam.dao.PublisherDao;
import com.epam.exception.NotExistEntityException;
import com.epam.model.periodical.Publisher;
import com.epam.util.ExceptionUtil;
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

    private static final String INSERT_PUBLISHER_SQL = "INSERT INTO publishers (id, name) VALUES " + " (DEFAULT, ?);";
    private static final String SELECT_PUBLISHER_BY_ID = "SELECT id,name FROM publishers WHERE id =?;";
    private static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM publishers;";
    private static final String DELETE_PUBLISHERS_SQL = "DELETE FROM publishers where id = ?;";
    private static final String UPDATE_PUBLISHER_SQL = "UPDATE publishers SET name = ? where id = ?;";
    private static final String CLEAR_TABLE_PUBLISHER_SQL = "DELETE FROM publishers";

    public static PublisherDaoImpl getInstance() {
        return PublisherDaoImpl.PublisherDaoImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public void insert(Publisher publisher) {
        LOGGER.info("INSERT PUBLISHER ID  {} NAME  {}", publisher.getId(), publisher.getName());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER_SQL)) {
            preparedStatement.setString(1, publisher.getName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.info("PUBLISHER CREATION FAILED");
            } else {
                LOGGER.info("PUBLISHER CREATION SUCCESSFUL");
            }

        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
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
            if (!rs.next()) {
                LOGGER.warn("THE PUBLISHER WITH ID {} ISN'T EXISTS", id);
                throw new NotExistEntityException(id);
            } else {
                String name = rs.getString("name");
                publisher = new Publisher(id, name);

            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return publisher;
    }

    private static class PublisherDaoImplHolder {
        private static final PublisherDaoImpl HOLDER_INSTANCE = new PublisherDaoImpl();
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
            if (!rowDeleted) {
                throw new NotExistEntityException(id);
            }
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
            statement.setString(1, publisher.getName());
            statement.setInt(2, publisher.getId());
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
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_PUBLISHER_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
