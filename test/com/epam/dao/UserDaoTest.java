package com.epam.dao;

import com.epam.dao.impl.UserDaoImpl;
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

public class UserDaoTest {

    public static final User USER = new User(1, "Serghei", "Ivanov", "ivanov_s@gmail.com", "ivanov", UserType.USER, "Gagarina street");
    public static final User USER1 = new User(2, "Alexandr", "Kabanov", "kabanov_a@gmail.com", "kabanov", UserType.USER, "Lenina street");
    public static final User USER2 = new User(3, "Петров", "Дмитрий", "petrov_d@gmail.com", "petrov", UserType.ADMIN, "Gagarina street");
    public static final User USER3 = new User(4, "Test", "Test", "test@gmail.com", "test", UserType.USER, "Gagarina street");

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoTest.class);
    private static final UserDao USER_DAO = new UserDaoImpl();
    private static List<User> USERS_LIST_FROM_DATABASE = USER_DAO.selectAll();

    @Before
    public void setUp() {
        LOGGER.info("SETUP USERS TEST");
        USER_DAO.clear();
        USER_DAO.insert(USER);
        USER_DAO.insert(USER1);
        USER_DAO.insert(USER2);
        USERS_LIST_FROM_DATABASE = USER_DAO.selectAll();
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
        User userForSelectTest = USER_DAO.select(USERS_LIST_FROM_DATABASE.get(0).getId());
        Assert.assertEquals(userForSelectTest.getFirstName(), USER.getFirstName());
    }

    @Test
    public void selectAll() {
        //TODO Дописать нормальный EQUALS в юзере.
        LOGGER.info("SELECT ALL FROM TABLE USERS TESTING");
        List<User> users = USER_DAO.selectAll();
        Assert.assertEquals(users.get(0), USER);
        Assert.assertEquals(users.get(1), USER1);
        Assert.assertEquals(users.get(2), USER2);
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE USER TESTING");
        USER_DAO.delete(USERS_LIST_FROM_DATABASE.get(0).getId());
        Assert.assertEquals(2, USER_DAO.selectAll().size());
    }

    @Test
    public void update() {
        //TODO SOMETHING WRONG
        LOGGER.info("UPDATE USER TESTING");
        User userForUpdateTest = USERS_LIST_FROM_DATABASE.get(0);
        userForUpdateTest.setFirstName("TESTING UPDATE FIRST NAME");
        USER_DAO.update(userForUpdateTest);
        Assert.assertNotEquals(userForUpdateTest, USER_DAO.select(userForUpdateTest.getId()));
    }

    @Test(expected = ExistEntityException.class)
    public void insertExisting() {
        LOGGER.info("INSERT EXISTING USER TESTING");
        USER_DAO.insert(USERS_LIST_FROM_DATABASE.get(0));
    }

    @Test(expected = NotExistEntityException.class)
    public void deleteNotExist() {
        USER_DAO.delete(525235);
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL USERS TESTING");
        USER_DAO.clear();
        Assert.assertEquals(0, USER_DAO.selectAll().size());
    }


    @Test
    public void selectByName() {
    }

    @Test
    public void selectByEmail() {

    }

    @Test
    public void selectByUserType() {
        List<User> users = USER_DAO.selectByUsersType(UserType.ADMIN);
        Assert.assertEquals(1, users.size());
    }

}