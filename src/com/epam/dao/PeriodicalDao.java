/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.model.periodical.Periodical;

import java.util.List;

public interface PeriodicalDao {

    void insert(Periodical periodical);

    Periodical select(Long id);

    List<Periodical> selectAll();

    boolean delete(Long id);

    boolean update(Periodical periodical);
    //for test only
    void clear();
}
