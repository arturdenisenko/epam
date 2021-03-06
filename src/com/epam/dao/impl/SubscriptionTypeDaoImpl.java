/*
 * @Denisenko Artur
 */

package com.epam.dao.impl;

import com.epam.dao.SubscriptionTypeDao;
import com.epam.exception.NotExistEntityException;
import com.epam.model.subscription.SubscriptionType;
import com.epam.pool.ConnectionPool;
import com.epam.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.epam.dao.impl.SQLQueries.*;

public class SubscriptionTypeDaoImpl implements SubscriptionTypeDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionTypeDaoImpl.class);

    public static SubscriptionTypeDaoImpl getInstance() {
        return SubscriptionTypeDaoImpl.SubscriptionTypeDaoImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public void insert(SubscriptionType subscriptionType) {
        LOGGER.info("INSERT SUBSCRIPTION TYPE {} {} {}", subscriptionType.getId(), subscriptionType.getName(), subscriptionType.getDurationByMonth());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBSCRIPTION_TYPE_CATEGORY_SQL)) {
            preparedStatement.setString(1, subscriptionType.getName());
            preparedStatement.setInt(2, subscriptionType.getDurationByMonth());
            preparedStatement.setFloat(3, subscriptionType.getPriceMultiplier());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                LOGGER.info("SUBSCRIPTION TYPE CREATION FAILED");
            } else {
                LOGGER.info("SUBSCRIPTION TYPE CREATION SUCCESSFUL");
            }
        } catch (SQLException e) {
            throw ExceptionUtil.convertException(e);
        }
    }

    @Override
    public SubscriptionType select(Long id) {
        LOGGER.info("SELECT FROM SUBSCRIPTION TYPE ID {}", id);
        SubscriptionType subscriptionType = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUBSCRIPTION_TYPE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistEntityException(id);
            } else {
                String name = rs.getString("name");
                int durationByMonth = rs.getInt("duration_by_month");
                Float priceMultiplier = rs.getFloat("price_multiplier");
                subscriptionType = new SubscriptionType(id, name, durationByMonth, priceMultiplier);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return subscriptionType;
    }

    @Override
    public List<SubscriptionType> selectAll() {
        LOGGER.info("SELECT ALL FROM SUBSCRIPTION TYPE");
        List<SubscriptionType> subscriptionTypes = new CopyOnWriteArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIPTION_TYPES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                int durationByMonth = rs.getInt("duration_by_month");
                Float priceMultiplier = rs.getFloat("price_multiplier");
                subscriptionTypes.add(new SubscriptionType(id, name, durationByMonth, priceMultiplier));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return subscriptionTypes;
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("DELETE SUBSCRIPTION TYPE WITH ID {}", id);
        boolean rowDeleted = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SUBSCRIPTION_TYPE_SQL)) {
            statement.setLong(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            if (!rowDeleted) {
                LOGGER.warn("SUBSCRIPTION TYPE WITH ID {} ISN'T DELETED", id);
                throw new NotExistEntityException(id);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(SubscriptionType subscriptionType) {
        LOGGER.info("UPDATE SUBSCRIPTION TYPE WITH ID {} AND NAME {}", subscriptionType.getId(), subscriptionType.getName());
        boolean rowUpdated = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SUBSCRIPTION_TYPE_SQL)) {
            statement.setString(1, subscriptionType.getName());
            statement.setInt(2, subscriptionType.getDurationByMonth());
            statement.setFloat(3, subscriptionType.getPriceMultiplier());
            statement.setLong(4, subscriptionType.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowUpdated;
    }

    //clear all Subs types, for tests only!
    @Override
    public void clear() {
        LOGGER.info("DELETE ALL SUBSCRIPTION TYPES  TABLE");
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_SUBSCRIPTION_TYPES_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static class SubscriptionTypeDaoImplHolder {
        private static final SubscriptionTypeDaoImpl HOLDER_INSTANCE = new SubscriptionTypeDaoImpl();
    }

}
