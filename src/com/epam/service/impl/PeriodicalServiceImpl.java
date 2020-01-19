
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

package com.epam.service.impl;

import com.epam.dao.PeriodicalDao;
import com.epam.filters.ModelFilter;
import com.epam.filters.PeriodicalSelectByNameFilter;
import com.epam.model.periodical.Periodical;
import com.epam.service.PeriodicalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeriodicalServiceImpl implements PeriodicalService {
    //TODO Обработать исключения
    private final static Logger LOGGER = LoggerFactory.getLogger(PeriodicalServiceImpl.class);
    private PeriodicalDao periodicalDao;


    public PeriodicalServiceImpl(PeriodicalDao periodicalDao) {
        LOGGER.info("INITIALIZING PERIODICAL SERVICE IMPL");
        this.periodicalDao = periodicalDao;

    }

    @Override
    public void createPeriodical(Periodical periodical) {
        LOGGER.info("CREATE PERIODICAL {}", periodical.toString());
        periodicalDao.insert(periodical);
    }

    @Override
    public Periodical getById(Long id) {
        LOGGER.info("GET PERIODICAL WITH ID  {}", id);
        return periodicalDao.selectPeriodicalById(id);
    }

    @Override
    public List<Periodical> getAllByName(String name) {
        LOGGER.info("SELECT PERIODICALS BY NAME {}", name);
        ModelFilter filter = new PeriodicalSelectByNameFilter();
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
        return periodicalDao.deletePeriodicalById(id);
    }

    @Override
    public boolean update(Periodical periodical) {
        LOGGER.info("UPDATE  PERIODICAL WITH ID {}", periodical.toString());
        return periodicalDao.update(periodical);
    }
}
