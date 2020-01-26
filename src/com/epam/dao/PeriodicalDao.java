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

package com.epam.dao;

import com.epam.model.periodical.Periodical;

import java.util.List;

public interface PeriodicalDao {
    //TODO periodical insert

    /**
     * This method insert new periodical in database
     *
     * @param periodical
     * @return Periodical with id
     */
    Periodical insert(Periodical periodical);

    /**
     * This method select periodical by id
     *
     * @param id periodical id to delete
     * @return periodical
     */
    Periodical selectPeriodicalById(Long id);

    /**
     * This method select all periodical from database
     *
     * @return List of Periodical
     */
    List<Periodical> selectAll();

    /**
     * This method delete periodical by ID
     *
     * @param id periodical id
     * @return true if delete success, false if something wrong
     */
    boolean deletePeriodicalById(Long id);

    /**
     * This method update periodical in database
     *
     * @param periodical periodical to update
     * @return true if delete success, false if something wrong
     */
    boolean updatePeriodical(Periodical periodical);

    /**
     * This method delete all  periodicals from database, for tests only not used in service layer
     */
    void clear();

    /**
     * This method select latest added periodicals.
     *
     * @param limit How much to find.
     * @return A List of periodical.
     */
    List<Periodical> selectLastPeriodicals(Integer limit);

    /**
     * This method finds a page of periodicals by category.
     *
     * @param categoryId Id of the category of periodicals to find
     * @param offset     Element to start from.
     * @param size       How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> selectPageByCategory(Long categoryId, Integer offset, Integer size);

    /**
     * This method finds a page of periodicals by publisher.
     *
     * @param publisherId Id of the publisher of periodicals to find
     * @param offset      Element to start from.
     * @param size        How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> selectPageByPublisher(Long publisherId, Integer offset, Integer size);

    /**
     * This methods finds page from all periodicals
     *
     * @param offset Element to start from.
     * @param size   How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> selectPage(Integer offset, Integer size);

    /**
     * This methods finds page from all periodicals by name.
     *
     * @param query  Search query.
     * @param offset Element to start from.
     * @param size   How much elements to take.
     * @return List of periodicals.
     */
    List<Periodical> selectPageByNameQuery(String query, Integer offset, Integer size);
}
