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

package com.epam.service;

import com.epam.model.periodical.Periodical;
import com.epam.util.Page;

import java.util.List;

public interface PeriodicalService {
    /**
     * @param periodical
     * @return new periodical
     */
    Periodical createPeriodical(Periodical periodical);

    /**
     * @param id of periodical
     * @return periodicals by id
     */
    Periodical getPeriodicalById(Long id);

    /**
     * @param name
     * @return list of periodicals by name
     */
    List<Periodical> getAllByName(String name);

    /**
     * @return list of all periodials
     */
    List<Periodical> getAll();

    /**
     * @param id periodical
     * @return true if delete is ok, false if failed
     */
    boolean removeById(Long id);

    /**
     * @param periodical
     * @return updated periodical
     */
    boolean update(Periodical periodical);

    /**
     * This method select latest added periodicals.
     *
     * @param limit How much to find.
     * @return A List of periodical.
     */
    List<Periodical> getLastAdded(Integer limit);

    /**
     *
     * @param page Number of the page, starts from 1
     * @param size How much elements to take.
     * @param categoryId id of Category
     * @return list of periodicals
     */
    Page<Periodical> getPageByCategory(Integer page, Integer size, Long categoryId);

    /**
     * @param page        Number of the page, starts from 1
     * @param size        How much elements to take.
     * @param publisherId id of Publisher
     * @return list of periodicals
     */
    Page<Periodical> getPageByPublisherId(Integer page, Integer size, Long publisherId);

    /**
     * This methods finds page from all periodicals
     *
     * @param page Number of the page, starts from 1.
     * @param size How much elements to take.
     * @return List of periodicals.
     */
    Page<Periodical> getPage(Integer page, Integer size);

    /**
     * This methods finds page from all periodicals by name.
     * @param query search query
     * @param page Number of the page, starts from 1.
     * @param size how much elements to return by page
     * @return LIST of periodicals
     */
    Page<Periodical> getPageByNameQuery(String query, Integer page, Integer size);
}
