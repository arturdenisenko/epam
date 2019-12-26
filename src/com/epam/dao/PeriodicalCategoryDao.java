package com.epam.dao;

import com.epam.model.periodical.PeriodicalCategory;

import java.util.List;

public interface PeriodicalCategoryDao {
    void insert(PeriodicalCategory periodicalCategory);

    PeriodicalCategory select(int id);

    List<PeriodicalCategory> selectAll();

    boolean delete(int id);

    boolean update(PeriodicalCategory periodicalCategory);

    //clear all publishers for test only
    void clear();
}
