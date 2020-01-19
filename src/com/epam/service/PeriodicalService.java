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

import java.util.List;

public interface PeriodicalService {

    void createPeriodical(Periodical periodical);

    Periodical getById(Long id);

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
    List<Periodical> getPageByCategory(Integer page, Integer size, Long categoryId);

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
     * @param offset Element to start from.
     * @param size   How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> getPage(Integer offset, Integer size);

    /**
     * This methods finds page from all periodicals by name.
     *
     * @param query  Search query.
     * @param offset Element to start from.
     * @param size   How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> getPageByNameQuery(String query, Integer offset, Integer size);
}
