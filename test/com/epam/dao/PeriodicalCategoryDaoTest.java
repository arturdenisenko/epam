package com.epam.dao;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.exception.ExistException;
import com.epam.model.periodical.PeriodicalCategory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeriodicalCategoryDaoTest {

    public static final PeriodicalCategory PERIODICAL_CATEGORY = new PeriodicalCategory(45, "Политика");
    public static final PeriodicalCategory PERIODICAL_CATEGORY1 = new PeriodicalCategory(46, "Спорт");
    public static final PeriodicalCategory PERIODICAL_CATEGORY2 = new PeriodicalCategory(47, "Здоровье");
    public static final PeriodicalCategory PERIODICAL_CATEGORY3 = new PeriodicalCategory(48, "Вне политики");
    private static final Logger LOGGER = LoggerFactory.getLogger(PublisherDaoTest.class);
    private static final PeriodicalCategoryDao PERIODICAL_CATEGORY_DAO = new PeriodicalCategoryDaoImpl();
    private static List<PeriodicalCategory> periodicalCategories = PERIODICAL_CATEGORY_DAO.selectAll();

    @Before
    public void setUp() {
        LOGGER.info("SETUP PERIODICALS CATEGORY TEST");
        PERIODICAL_CATEGORY_DAO.clear();
        PERIODICAL_CATEGORY_DAO.insert(PERIODICAL_CATEGORY);
        PERIODICAL_CATEGORY_DAO.insert(PERIODICAL_CATEGORY1);
        PERIODICAL_CATEGORY_DAO.insert(PERIODICAL_CATEGORY2);
        periodicalCategories = PERIODICAL_CATEGORY_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT PERIODICAL CATEGORY TESTING");
        PERIODICAL_CATEGORY_DAO.insert(PERIODICAL_CATEGORY3);
        Assert.assertEquals(4, PERIODICAL_CATEGORY_DAO.selectAll().size());

    }

    @Test
    public void select() {
        LOGGER.info("SELECT FROM DATABASE PERIODICAL CATEGORY TESTING");
        PeriodicalCategory periodicalCategoryTest = PERIODICAL_CATEGORY_DAO.select(periodicalCategories.get(0).getId());
        Assert.assertEquals(periodicalCategoryTest.getName(), PERIODICAL_CATEGORY.getName());
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM DATABASE PERIODICAL CATEGORY TESTING");
        List<PeriodicalCategory> periodicalCategories = PERIODICAL_CATEGORY_DAO.selectAll();
        Assert.assertEquals(periodicalCategories.get(0).getName(), PERIODICAL_CATEGORY.getName());
        Assert.assertEquals(periodicalCategories.get(1).getName(), PERIODICAL_CATEGORY1.getName());
        Assert.assertEquals(periodicalCategories.get(2).getName(), PERIODICAL_CATEGORY2.getName());
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE PERIODICAL CATEGORY TESTING");
        Assert.assertTrue(PERIODICAL_CATEGORY_DAO.delete(periodicalCategories.get(0).getId()));
        Assert.assertEquals(2, PERIODICAL_CATEGORY_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE PERIODICAL CATEGORY TESTING");
        PeriodicalCategory periodicalCategoryUpdate = periodicalCategories.get(0);
        periodicalCategoryUpdate.setName("TESTING UPDATE NAME PERIODICAL CATEGORY");
        PERIODICAL_CATEGORY_DAO.update(periodicalCategoryUpdate);
        Assert.assertEquals(periodicalCategoryUpdate.getName(), PERIODICAL_CATEGORY_DAO.select(periodicalCategoryUpdate.getId()).getName());
    }

    @Test(expected = ExistException.class)
    public void insertExisting() {
        LOGGER.info("INSERT EXISTING PERIODICAL CATEGORY TESTING");
        LOGGER.info(periodicalCategories.get(0).toString());
        LOGGER.info(periodicalCategories.toString());
        PERIODICAL_CATEGORY_DAO.insert(periodicalCategories.get(0));
        LOGGER.info(PERIODICAL_CATEGORY_DAO.selectAll().toString());
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL PERIODICAL CATEGORY TESTING");
        PERIODICAL_CATEGORY_DAO.clear();
        Assert.assertEquals(0, PERIODICAL_CATEGORY_DAO.selectAll().size());
    }
}