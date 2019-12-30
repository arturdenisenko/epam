package com.epam.dao.impl;

import com.epam.dao.SubscriptionTypeDao;
import com.epam.model.subscription.SubscriptionType;
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

public class SubscriptionTypeDaoImpl implements SubscriptionTypeDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionTypeDaoImpl.class);

    private static final String INSERT_SUBSCRIPTION_TYPE_CATEGORY_SQL =
            "INSERT INTO subscription_type" + "  (id, name, duration_by_month, price_multiplier  ) VALUES " + " (DEFAULT, ?,?,?);";
    private static final String SELECT_SUBSCRIPTION_TYPE_BY_ID = "SELECT id,name FROM periodical_category WHERE id =?;";
    private static final String SELECT_ALL_SUBSCRIPTION_TYPES = "SELECT * FROM subscription_type ORDER BY id;";
    private static final String DELETE_SUBSCRIPTION_TYPE_SQL = "DELETE FROM periodical_category where id = ?;";
    private static final String UPDATE_SUBSCRIPTION_TYPE_SQL = "UPDATE periodical_category SET name = ? where id = ?;";
    private static final String CLEAR_TABLE_SUBSCRIPTION_TYPES_SQL = "DELETE FROM subscription_type";

    public static SubscriptionTypeDaoImpl getInstance() {
        return SubscriptionTypeDaoImpl.SubscriptionTypeDaoImplHolder.HOLDER_INSTANCE;
    }

    @Override
    public void insert(SubscriptionType subscriptionType) {
        LOGGER.info("INSERT SUBSCRIPTION TYPE {} {} {}", subscriptionType.getId(), subscriptionType.getName(), subscriptionType.getDurationByMonth());
        try (Connection connection = JDBCUtils.getConnection();
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
    public SubscriptionType select(int id) {
        return null;
    }

    @Override
    public List<SubscriptionType> selectAll() {
        LOGGER.info("SELECT ALL FROM SUBSCRIPTION TYPE");
        List<SubscriptionType> subscriptionTypes = new CopyOnWriteArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBSCRIPTION_TYPES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
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
    public boolean delete(int id) {
        LOGGER.info("DELETE SUBSCRIPTION TYPE WITH ID {}", id);
        return true;
    }

    @Override
    public boolean update(SubscriptionType subscriptionType) {
        LOGGER.info("UPDATE SUBSCRIPTION TYPE WITH ID {} AND NAME {}", subscriptionType.getId(), subscriptionType.getName());
        return true;
    }

    //clear all periodical categories, for tests only!
    @Override
    public void clear() {
        LOGGER.info("DELETE ALL CATEGORIES IN PERIODICAL CATEGORY TABLE");
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_SUBSCRIPTION_TYPES_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private static class SubscriptionTypeDaoImplHolder {
        private static final SubscriptionTypeDaoImpl HOLDER_INSTANCE = new SubscriptionTypeDaoImpl();
    }

    private static class PeriodicalCategoryDaoImplHolder {
        private static final PeriodicalCategoryDaoImpl HOLDER_INSTANCE = new PeriodicalCategoryDaoImpl();
    }
}
