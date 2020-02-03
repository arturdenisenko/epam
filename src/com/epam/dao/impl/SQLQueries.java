/*
 * @Denisenko Artur
 */

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
            ", periodical_category = ?, periodicity_in_six_month = ?, min_subscription_period=?, cost_per_month=?, active =?, imagelink = ? " +
            "where id = ?;";
    protected static final String CLEAR_TABLE_PERIODICAL_SQL = "DELETE FROM periodicals";
    protected static final String FIND_LAST_PERIODICALS = "SELECT * FROM periodicals ORDER BY id DESC LIMIT ?";
    protected static final String SELECT_PAGE_BY_PUBLISHER_ID = "SELECT * FROM periodicals WHERE publisher_fk = ? ORDER BY id DESC LIMIT ? OFFSET ?";
    protected static final String SELECT_PAGE_BY_CATEGORY_QUERY = "SELECT * FROM periodicals WHERE periodical_category = ? ORDER BY id DESC LIMIT ? OFFSET ?";
    protected static final String SELECT_PERIODICAL_PAGE = "SELECT * FROM periodicals ORDER BY id DESC LIMIT ? OFFSET ?";
    protected static final String SELECT_PAGE_BY_NAME = "SELECT * FROM periodicals WHERE name LIKE ? LIMIT ? OFFSET ?";

    //periodical category

    protected static final String INSERT_PERIODICAL_CATEGORY_SQL = "INSERT INTO periodical_category" + "  (id, name) VALUES " + " (DEFAULT, ?);";
    protected static final String SELECT_PERIODICAL_CATEGORY_BY_ID = "SELECT id,name FROM periodical_category WHERE id =?;";
    protected static final String SELECT_ALL_PERIODICAL_CATEGORY = "SELECT * FROM periodical_category ORDER BY id;";
    protected static final String DELETE_PERIODICAL_CATEGORY_SQL = "DELETE FROM periodical_category where id = ?;";
    protected static final String UPDATE_PERIODICAL_CATEGORY_SQL = "UPDATE periodical_category SET name = ? where id = ?;";
    protected static final String CLEAR_TABLE_PERIODICAL_CATEGORY_SQL = "DELETE FROM periodical_category";

    //publishers

    protected static final String INSERT_PUBLISHER_SQL = "INSERT INTO publishers (id, name) VALUES " + " (DEFAULT, ?);";
    protected static final String SELECT_PUBLISHER_BY_ID = "SELECT id,name FROM publishers WHERE id =?;";
    protected static final String SELECT_ALL_PUBLISHERS = "SELECT * FROM publishers;";
    protected static final String DELETE_PUBLISHERS_SQL = "DELETE FROM publishers where id = ?;";
    protected static final String UPDATE_PUBLISHER_SQL = "UPDATE publishers SET name = ? where id = ?;";
    protected static final String CLEAR_TABLE_PUBLISHER_SQL = "DELETE FROM publishers";
    //subscriptions

    protected static final String INSERT_SUBSCRIPTION_SQL =
            "INSERT INTO subscriptions (id, user_id, periodical_id,start_date,end_date, cost, subscription_type_id) " +
                    "VALUES " + " (DEFAULT, ?, ?, ?, ?, ?, ?);";
    protected static final String SELECT_SUBSCRIPTION_BY_ID = "SELECT * FROM subscriptions WHERE id =?;";
    protected static final String SELECT_SUBSCRIPTION_BY_USER_ID = "SELECT * FROM subscriptions WHERE user_id =?;";
    protected static final String SELECT_ALL_SUBSCRIPTIONS = "SELECT * FROM subscriptions;";
    protected static final String DELETE_SUBSCRIPTION_SQL = "DELETE FROM subscriptions where id = ?;";
    protected static final String UPDATE_SUBSCRIPTIONS_SQL =
            "UPDATE subscriptions SET user_id = ?, periodical_id = ?,start_date = ?,end_date = ?, cost = ?, " +
                    "subscription_type_id =? where id = ?;";
    protected static final String CLEAR_TABLE_SUBSCRIPTION_SQL = "DELETE FROM subscriptions";
    protected static final String CHECK_IF_USER_SUBSCRIBED_QUERY =
            "SELECT * FROM subscriptions WHERE user_id = ? AND periodical_id = ? AND CURRENT_DATE BETWEEN start_date " +
                    "AND end_date;";
    protected static final String SELECT_SUBSCRIPTION_PAGE = "SELECT * FROM subscriptions ORDER BY start_date DESC LIMIT ? OFFSET ?";
    //subscription type
    protected static final String INSERT_SUBSCRIPTION_TYPE_CATEGORY_SQL =
            "INSERT INTO subscription_type" + "  (id, name, duration_by_month, price_multiplier) VALUES " + " (DEFAULT, ?,?,?);";
    protected static final String SELECT_SUBSCRIPTION_TYPE_BY_ID = "SELECT * FROM subscription_type WHERE id =?;";
    protected static final String SELECT_ALL_SUBSCRIPTION_TYPES = "SELECT * FROM subscription_type ORDER BY id;";
    protected static final String DELETE_SUBSCRIPTION_TYPE_SQL = "DELETE FROM subscription_type WHERE id = ?;";
    protected static final String UPDATE_SUBSCRIPTION_TYPE_SQL = "UPDATE subscription_type SET name = ?, duration_by_month = ?, price_multiplier = ? where id = ?;";
    protected static final String CLEAR_TABLE_SUBSCRIPTION_TYPES_SQL = "DELETE FROM subscription_type";

    //users
    protected static final String INSERT_USER_SQL = "INSERT INTO users" + "  " +
            "(id, first_name, last_name, email, password, address, user_type, balance) VALUES " + " (DEFAULT, ?,?,?,?,?,?,?);";
    protected static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id =?;";
    protected static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email =?;";
    protected static final String SELECT_ALL_USERS = "SELECT * FROM users ORDER BY id;";
    protected static final String DELETE_USER_SQL = "DELETE FROM users where id = ?;";
    protected static final String UPDATE_USER_SQL =
            "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, address = ?, user_type = ?, balance = ? where id = ?;";
    protected static final String CLEAR_TABLE_USERS_SQL = "DELETE FROM users";
    protected static final String SELECT_ALL_BY_USER_TYPE =
            "SELECT * FROM users WHERE user_type = ? ORDER BY id DESC LIMIT ? OFFSET ?";
}

