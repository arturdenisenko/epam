/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.dao.impl;

/**
 * This class to store sql create to queries
 */
public class SQLQueries {

    //periodical queries
    protected static final String INSERT_PERIODICAL_SQL =
            "INSERT INTO periodicals (id, name, about, publisher_fk, periodical_category, " +
                    "periodicity_in_six_month, min_subscription_period, cost_per_month, active, imagelink) VALUES " + " " +
                    "(DEFAULT, ?,?,?,?,?,?,?,?,?);";
    protected static final String SELECT_PERIODICAL_BY_ID = "SELECT * FROM periodicals WHERE id =?;";
    protected static final String SELECT_ALL_PERIODICALS = "SELECT * FROM periodicals;";
    protected static final String DELETE_PERIODICAL_SQL = "DELETE FROM periodicals where id = ?;";
    protected static final String UPDATE_PERIODICAL_SQL = "UPDATE periodicals SET name = ?, about = ?,publisher_fk= ?" +
            ", periodical_category = ?, periodicity_in_six_month = ?, min_subscription_period=?, cost_per_month=?, active =?, imagelink =? " +
            "where id = ?;";
    protected static final String CLEAR_TABLE_PERIODICAL_SQL = "DELETE FROM periodicals";
    protected static final String FIND_LAST_PERIODICALS = "SELECT * FROM periodicals ORDER BY id DESC LIMIT ?";
    protected static final String SELECT_PAGE_BY_PUBLISHER_ID = "SELECT * FROM periodicals WHERE publisher_fk = ? ORDER BY id DESC LIMIT ? OFFSET ?";
    protected static final String SELECT_PAGE_BY_CATEGORY_QUERY = "SELECT * FROM periodicals WHERE periodical_category = ? ORDER BY id DESC LIMIT ? OFFSET ?";
    protected static final String SELECT_PAGE = "SELECT * FROM periodicals ORDER BY id DESC LIMIT ? OFFSET ?";
    protected static final String SELECT_PAGE_BY_NAME = "SELECT * FROM periodicals WHERE name LIKE ? LIMIT ? OFFSET ?";

}
