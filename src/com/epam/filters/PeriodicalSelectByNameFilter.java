/*
 * @Denisenko Artur
 */

package com.epam.filters;

import com.epam.model.periodical.Periodical;

import java.util.ArrayList;
import java.util.List;

public class PeriodicalSelectByNameFilter implements ModelFilter<Periodical, String> {
    @Override
    public List<Periodical> meetCriteria(List<Periodical> periodicals, String name) {
        List<Periodical> periodicalList = new ArrayList<>();
        for (Periodical periodical : periodicals) {
            if (periodical.getName().contains(name) || periodical.getAbout().contains(name)) {
                periodicalList.add(periodical);
            }
        }
        return periodicalList;
    }
}
