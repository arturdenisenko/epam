/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.PeriodicalCategoryDao;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.service.PeriodicalCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeriodicalCategoryServiceImpl implements PeriodicalCategoryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PeriodicalCategoryServiceImpl.class);
    private PeriodicalCategoryDao periodicalCategoryDao;

    public PeriodicalCategoryServiceImpl(PeriodicalCategoryDao periodicalCategoryDao) {
        LOGGER.info("INITIALIZING PERIODICAL CATEGORY SERVICE IMPL");
        this.periodicalCategoryDao = periodicalCategoryDao;
    }

    @Override
    public void create(PeriodicalCategory periodicalCategory) {
        LOGGER.info("CREATE PERIODICAL CATEGORY  {}", periodicalCategory.toString());
        periodicalCategoryDao.insert(periodicalCategory);
    }

    @Override
    public PeriodicalCategory getById(Long id) {
        LOGGER.info("GET PERIODICAL CATEGORY WITH ID  {}", id);
        return periodicalCategoryDao.select(id);
    }

    @Override
    public List<PeriodicalCategory> getAll() {
        LOGGER.info("GET ALL PERIODICAL CATEGORIES");
        return periodicalCategoryDao.selectAll();
    }

    @Override
    public boolean deleteById(Long id) {
        LOGGER.info("DELETE PERIODICAL CATEGORY WITH ID {}", id);
        return periodicalCategoryDao.delete(id);
    }

    @Override
    public boolean update(PeriodicalCategory periodicalCategory) {
        LOGGER.info("UPDATE PERIODICAL CATEGORY WITH ID {}", periodicalCategory.toString());
        return periodicalCategoryDao.update(periodicalCategory);
    }

}
