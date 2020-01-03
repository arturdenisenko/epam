package com.epam.dao;

import com.epam.model.periodical.PeriodicalCategory;

import java.util.List;

public interface PeriodicalCategoryDao {
    void insert(PeriodicalCategory periodicalCategory);

    PeriodicalCategory select(Long id);

    List<PeriodicalCategory> selectAll();

    boolean delete(Long id);

    boolean update(PeriodicalCategory periodicalCategory);

    //clear all publishers for test only
    void clear();
}
