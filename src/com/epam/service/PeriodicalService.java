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

    void createPeriodical(Periodical periodical);

    Periodical getPeriodicalById(Long id);

    List<Periodical> getAllByName(String name);

    List<Periodical> getAll();

    boolean removeById(Long id);

    boolean update(Periodical periodical);

    /**
     * This method select latest added periodicals.
     *
     * @param limit How much to find.
     * @return A List of periodical.
     */
    List<Periodical> getLastAdded(Integer limit);

    /**
     * This method finds a page of periodicals by category.
     *
     * @param categoryId Id of the category of periodicals to find
     * @param offset     Element to start from.
     * @param size       How much elements to take.
     * @return List of periodicals.
     */
    Page<Periodical> getPageByCategory(Integer page, Integer size, Long categoryId);

    /**
     * This method finds a page of periodicals by publisher.
     *
     * @param publisherId Id of the publisher of periodicals to find
     * @param offset      Element to start from.
     * @param size        How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> getPageByPublisher(Long publisherId, Integer offset, Integer size);

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
