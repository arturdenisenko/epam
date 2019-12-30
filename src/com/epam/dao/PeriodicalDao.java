package com.epam.dao;

import com.epam.model.periodical.Periodical;

import java.util.List;

public interface PeriodicalDao {

    void insert(Periodical periodical);

    Periodical select(int id);

    List<Periodical> selectByName(String name);


    List<Periodical> selectAll();

    boolean delete(int id);

    boolean update(Periodical periodical);

    //for test only
    void clear();
}
