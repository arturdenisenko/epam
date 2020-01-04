package com.epam.service;

import com.epam.model.periodical.PeriodicalCategory;

import java.util.List;

public interface PeriodicalCategoryService {

    void insert(PeriodicalCategory periodicalCategory);

    PeriodicalCategory select(Long id);

    List<PeriodicalCategory> selectAll();

    boolean delete(Long id);

    boolean update(PeriodicalCategory periodicalCategory);

}
