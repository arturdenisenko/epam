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

}
