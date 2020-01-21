
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
import com.epam.util.Page;
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
    public Periodical getPeriodicalById(Long id) {
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
        return periodicalDao.updatePeriodical(periodical);
    }

    @Override
    public List<Periodical> getLastAdded(Integer limit) {
        LOGGER.info("FINDING {} LAST PERIODICALS", limit);

        if (limit == null) {
            return null;
        }
        return periodicalDao.selectLastPeriodicals((limit));
    }

    @Override
    public Page<Periodical> getPageByCategory(Integer page, Integer size, Long categoryId) {
        LOGGER.info("Getting page number {} of size {} for category id {}", page, size, categoryId);

        if (page == null || size == null || categoryId == null || page < 1 || size < 1) {
            return null;
        }

        List<Periodical> items = periodicalDao.selectPageByCategory(categoryId, (page - 1) * size, size);
        return new Page(items, page, size);
    }

    @Override
    public List<Periodical> getPageByPublisher(Long publisherId, Integer offset, Integer size) {
        return null;
    }

    @Override
    public Page<Periodical> getPage(Integer page, Integer size) {
        LOGGER.info("Getting page number {} of size {}", page, size);

        if (page == null || size == null || page < 1 || size < 1) {
            return null;
        }

        List<Periodical> items = periodicalDao.selectPage((page - 1) * size, size);
        return new Page(items, page, size);
    }

    @Override
    public Page<Periodical> getPageByNameQuery(String query, Integer page, Integer size) {
        LOGGER.info("Getting page by query {} number {}, of size {}", query, page, size);

        if (page == null || size == null || query == null || page < 1 || size < 1) {
            return null;
        }

        List<Periodical> items = periodicalDao.selectPageByNameQuery(query, (page - 1) * size, size);
        return new Page(items, page, size);
    }
}
