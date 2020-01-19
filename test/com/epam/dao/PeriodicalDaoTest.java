/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.model.periodical.Periodical;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.epam.TestData.*;

public class PeriodicalDaoTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicalDaoTest.class);

    @Before
    public void setUp() {
        LOGGER.info("SETUP PERIODICALS TEST");
        PERIODICAL_DAO.clear();
        PERIODICAL_DAO.insert(PERIODICAL);
        PERIODICAL_DAO.insert(PERIODICAL1);
        PERIODICAL_DAO.insert(PERIODICAL2);
        periodicalListFromDatabase = PERIODICAL_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT PERIODICAL TESTING");
        PERIODICAL_DAO.insert(PERIODICAL3);
        Assert.assertEquals(4, PERIODICAL_DAO.selectAll().size());
    }

    @Test
    public void select() {
        LOGGER.info("SELECT PERIODICAL DATABASE USER TESTING");
        Periodical periodicalForSelectTest = PERIODICAL_DAO.selectPeriodicalById(periodicalListFromDatabase.get(0).getId());
        Assert.assertEquals(periodicalForSelectTest, PERIODICAL);
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM TABLE PERIODICALS TESTING");
        List<Periodical> users = PERIODICAL_DAO.selectAll();
        Assert.assertEquals(users.get(0), PERIODICAL);
        Assert.assertEquals(users.get(1), PERIODICAL1);
        Assert.assertEquals(users.get(2), PERIODICAL2);
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE PERIODICAL TESTING");
        PERIODICAL_DAO.deletePeriodicalById(periodicalListFromDatabase.get(0).getId());
        Assert.assertEquals(2, PERIODICAL_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE PERIODICAL TESTING");
        Periodical periodicalForUpdateTest = periodicalListFromDatabase.get(0);
        periodicalForUpdateTest.setName("TESTING UPDATE NAME");
        PERIODICAL_DAO.update(periodicalForUpdateTest);
        Assert.assertEquals(periodicalForUpdateTest, PERIODICAL_DAO.selectPeriodicalById(periodicalForUpdateTest.getId()));
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL PERIODICAL TESTING");
        PERIODICAL_DAO.clear();
        Assert.assertEquals(0, PERIODICAL_DAO.selectAll().size());
    }
}