package com.epam.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                PeriodicalDao.class,
                PeriodicalCategoryDaoTest.class,
                PublisherDaoTest.class,
                UserDaoTest.class,
        })
public class AllDAOTest {

}
