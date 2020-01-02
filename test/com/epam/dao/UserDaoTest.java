package com.epam.dao;

import com.epam.exception.ExistEntityException;
import com.epam.exception.NotExistEntityException;
import com.epam.model.user.User;
import com.epam.model.user.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.TestData.*;

public class UserDaoTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);

    @Before
    public void setUp() {
        LOGGER.info("SETUP USERS TEST");
        USER_DAO.clear();
        USER_DAO.insert(USER);
        USER_DAO.insert(USER1);
        USER_DAO.insert(USER2);
        userList = USER_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT USER TESTING");
        USER_DAO.insert(USER3);
        Assert.assertEquals(4, USER_DAO.selectAll().size());
    }

    @Test
    public void select() {
        LOGGER.info("SELECT FROM DATABASE USER TESTING");
        User userForSelectTest = USER_DAO.select(userList.get(0).getId());
        Assert.assertEquals(userForSelectTest.getFirstName(), USER.getFirstName());
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM TABLE USERS TESTING");
        List<User> users = USER_DAO.selectAll();
        Assert.assertEquals(users.get(0), USER);
        Assert.assertEquals(users.get(1), USER1);
        Assert.assertEquals(users.get(2), USER2);
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE USER TESTING");
        USER_DAO.delete(userList.get(0).getId());
        Assert.assertEquals(2, USER_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE USER TESTING");
        User userForUpdateTest = userList.get(0);
        userForUpdateTest.setFirstName("TESTING UPDATE FIRST NAME");
        USER_DAO.update(userForUpdateTest);
        Assert.assertEquals(userForUpdateTest, USER_DAO.select(userForUpdateTest.getId()));
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL USERS TESTING");
        USER_DAO.clear();
        Assert.assertEquals(0, USER_DAO.selectAll().size());
    }


    @Test
    public void selectByName() {
        LOGGER.info("SEARCH BY LAST OR FIRST NAME TESTING");
        List<User> usersContains = USER_DAO.selectByName("Ivan");
        Assert.assertEquals(2, usersContains.size());
    }

    @Test
    public void selectByEmail() {
        LOGGER.info("SELECT FROM DATABASE BY EMAIL USER TESTING");
        User userForSelectTest = USER_DAO.selectByEmail(USER.getEmail());
        Assert.assertEquals(userForSelectTest, USER);
    }

    @Test
    public void selectByUserType() {
        LOGGER.info("SELECT FROM DATABASE BY USER TYPE USER TESTING");
        List<User> users = USER_DAO.selectByUsersType(UserType.ADMIN);
        Assert.assertEquals(1, users.size());
    }

    @Test(expected = NotExistEntityException.class)
    public void selectNotExist() {
        LOGGER.info("SELECT NOT EXISTING USER TESTING");
        USER_DAO.select(525235);
    }

    @Test(expected = ExistEntityException.class)
    public void insertExisting() {
        LOGGER.info("INSERT EXISTING USER TESTING");
        USER_DAO.insert(userList.get(0));
    }

    @Test(expected = NotExistEntityException.class)
    public void deleteNotExist() {
        LOGGER.info("DELETE NOT EXISTING USER TESTING");
        USER_DAO.delete(525235);
    }


}