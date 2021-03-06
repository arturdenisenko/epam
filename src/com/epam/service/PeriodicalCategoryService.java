/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.periodical.PeriodicalCategory;

import java.util.List;

public interface PeriodicalCategoryService {

    PeriodicalCategory createPeriodicalCategory(PeriodicalCategory periodicalCategory);

    PeriodicalCategory getById(Long id);

    List<PeriodicalCategory> getAll();

    boolean deleteById(Long id);

    boolean update(PeriodicalCategory periodicalCategory);

}
