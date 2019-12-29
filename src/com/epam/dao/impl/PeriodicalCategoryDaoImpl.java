package com.epam.dao.impl;

import com.epam.dao.PeriodicalCategoryDao;
import com.epam.exception.ExistEntityException;
import com.epam.exception.NotExistEntityException;
import com.epam.model.periodical.PeriodicalCategory;
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

public class PeriodicalCategoryDaoImpl implements PeriodicalCategoryDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PeriodicalCategoryDaoImpl.class);

    private static final String INSERT_PERIODICAL_CATEGORY_SQL = "INSERT INTO periodical_category" + "  (id, name) VALUES " + " (DEFAULT, ?);";
    private static final String SELECT_PERIODICAL_CATEGORY_BY_ID = "SELECT id,name FROM periodical_category WHERE id =?;";
    private static final String SELECT_ALL_PERIODICAL_CATEGORY = "SELECT * FROM periodical_category;";
    private static final String DELETE_PERIODICAL_CATEGORY_SQL = "DELETE FROM periodical_category where id = ?;";
    private static final String UPDATE_PERIODICAL_CATEGORY_SQL = "UPDATE periodical_category SET name = ? where id = ?;";
    private static final String CLEAR_TABLE_PERIODICAL_CATEGORY_SQL = "DELETE FROM periodical_category";


    public PeriodicalCategoryDaoImpl() {
    }

    @Override
    public void insert(PeriodicalCategory periodicalCategory) {
        LOGGER.info("INSERT PERIODICAL CATEGORY {} {}", periodicalCategory.getId(), periodicalCategory.getName());
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERIODICAL_CATEGORY_SQL)) {
            preparedStatement.setString(1, periodicalCategory.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                throw ExceptionUtil.convertException(e);
            } catch (ExistEntityException ex) {
                LOGGER.error(ex.getMessage(), ex);
            }
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public PeriodicalCategory select(int id) {
        LOGGER.info("SELECT FROM PERIODICAL CATEGORY ID {}", id);
        PeriodicalCategory periodicalCategory = null;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PERIODICAL_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (!rs.next()) {
                throw new NotExistEntityException(id);
            }
            while (rs.next()) {
                String name = rs.getString("name");
                periodicalCategory = new PeriodicalCategory(id, name);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return periodicalCategory;
    }

    @Override
    public List<PeriodicalCategory> selectAll() {
        LOGGER.info("SELECT ALL FROM PERIODICAL CATEGORY");
        List<PeriodicalCategory> periodicalCategories = new CopyOnWriteArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PERIODICAL_CATEGORY)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                periodicalCategories.add(new PeriodicalCategory(id, name));
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return periodicalCategories;
    }

    @Override
    public boolean delete(int id) {
        LOGGER.info("DELETE PERIODICAL CATEGORY WITH ID {}", id);
        boolean rowDeleted = false;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PERIODICAL_CATEGORY_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
            if (!rowDeleted) {
                LOGGER.warn("PERIODICAL WITH ID {} ISN'T DELETED", id);
                throw new NotExistEntityException(id);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowDeleted;
    }

    @Override
    public boolean update(PeriodicalCategory periodicalCategory) {
        LOGGER.info("UPDATE PERIODICAL CATEGORY WITH ID {} AND NAME {}", periodicalCategory.getId(), periodicalCategory.getName());
        boolean rowUpdated = false;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PERIODICAL_CATEGORY_SQL)) {
            statement.setString(1, periodicalCategory.getName());
            statement.setInt(2, periodicalCategory.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return rowUpdated;
    }

    //clear all periodical categories, for tests only!
    @Override
    public void clear() {
        LOGGER.info("DELETE ALL CATEGORIES IN PERIODICAL CATEGORY TABLE");
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(CLEAR_TABLE_PERIODICAL_CATEGORY_SQL)) {
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
