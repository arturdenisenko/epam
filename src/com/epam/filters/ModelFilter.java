/*
 * @Denisenko Artur
 */

package com.epam.filters;

import java.util.List;

public interface ModelFilter<T, K> {
    List<T> meetCriteria(List<T> t, K k);
}
