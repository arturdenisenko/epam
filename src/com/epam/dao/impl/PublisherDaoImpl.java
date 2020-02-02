/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.dao.impl;

import com.epam.dao.PublisherDao;
import com.epam.exception.DaoException;
import com.epam.exception.NotExistEntityException;
import com.epam.model.periodical.Publisher;
import com.epam.pool.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.epam.dao.impl.SQLQueries.*;

public class PublisherDaoImpl implements PublisherDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PublisherDaoImpl.class);

    public static PublisherDaoImpl getInstance() {
        return PublisherDaoImpl.PublisherDaoImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public Publisher insertPublisher(Publisher publisher) throws DaoException {
        LOGGER.info("INSERT PUBLISHER ID  {} NAME  {}", publisher.getId(), publisher.getName());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER_SQL)) {
            preparedStatement.setString(1, publisher.getName());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.info("PUBLISHER CREATION FAILED");
            } else {
                LOGGER.info("PUBLISHER CREATION SUCCESSFUL");
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        publisher.setId(generatedKeys.getLong(1));
                    } else {
                        LOGGER.error("Failed to create publisher, no ID obtained.");
                    }
                }
            }

        } catch (SQLException e) {
            throw new DaoException("PUBLISHER CREATION FAILED" + e.getMessage(), e);
        }
        return publisher;
    }

    @Override
    public Publisher select(Long id) throws DaoException {
        LOGGER.info("SELECT PUBLISHER WITH ID {}", id);
        Publisher publisher = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PUBLISHER_BY_ID)) {
            preparedStatement.setLong(1, id);
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
            throw new DaoException("Publisher with id is not exist" + e, e);
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

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PUBLISHERS);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Publisher publisher = new Publisher();
                publisher.setId(result.getLong("id"));
                publisher.setName(result.getString("name"));
                publishers.add(publisher);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return publishers;
    }

    @Override
    public boolean delete(Long id) throws DaoException {
        LOGGER.info("DELETE PUBLISHER WITH ID = {} ", id);
        boolean rowDeleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PUBLISHERS_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            /*if (!rowDeleted) {
                throw new NotExistEntityException(id);
            }*/
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException("PUBLISHER DELETE IS FAILED", e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(Publisher publisher) throws DaoException {
        LOGGER.info("UPDATE PUBLISHER WITH ID  = {} NAME = {} ", publisher.getId(), publisher.getName());
        boolean rowUpdated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PUBLISHER_SQL)) {
            statement.setString(1, publisher.getName());
            statement.setLong(2, publisher.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException("PUBLISHER UPDATE IS FAILED" + e, e);
        }
        return rowUpdated;
    }

    //clear all publishers for test only
    @Override
    public void clear() throws DaoException {
        LOGGER.info("DELETE ALL PUBLISHERS");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_PUBLISHER_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException("The table can't be clear" + e, e);
        }
    }
}
