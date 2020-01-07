
/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.PeriodicalDao;
import com.epam.filters.Filter;
import com.epam.filters.PeriodicalSelectByNameFilter;
import com.epam.model.periodical.Periodical;
import com.epam.service.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {
    //TODO Обработать исключения
    private final static Logger LOGGER = LoggerFactory.getLogger(PublisherServiceImpl.class);
    private PeriodicalDao periodicalDao;

    public PeriodicalServiceImpl(PeriodicalDao periodicalDao) {
        LOGGER.info("INITIALIZING PERIODICAL SERVICE IMPL");
        this.periodicalDao = periodicalDao;
    }

    @Override
    public void create(Periodical periodical) {
        LOGGER.info("CREATE PERIODICAL {}", periodical.toString());
        periodicalDao.insert(periodical);
    }

    @Override
    public Periodical getById(Long id) {
        LOGGER.info("GET PERIODICAL WITH ID  {}", id);
        return periodicalDao.select(id);
    }

    @Override
    public List<Periodical> getAllByName(String name) {
        LOGGER.info("SELECT PERIODICALS BY NAME {}", name);
        Filter filter = new PeriodicalSelectByNameFilter();
        return filter.meetCriteria(getAll(), name);
    }

    @Override
    public List<Periodical> getAll() {
        LOGGER.info("GET ALL PERIODICALS");
        return periodicalDao.selectAll();
    }

    @Override
    public boolean removeById(Long id) {
        LOGGER.info("REMOVE PERIODICAL WITH ID {}", id);
        return periodicalDao.delete(id);
    }

    @Override
    public boolean update(Periodical periodical) {
        LOGGER.info("UPDATE  PERIODICAL WITH ID {}", periodical.toString());
        return periodicalDao.update(periodical);
    }
}
