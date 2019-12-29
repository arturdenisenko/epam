package com.epam.filters;

import java.util.List;

public interface Filter<T, K> {
    List<T> meetCriteria(List<T> t, K k);
}
