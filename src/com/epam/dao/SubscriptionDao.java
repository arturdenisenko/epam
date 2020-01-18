/*
 * @Denisenko Artur
 */

package com.epam.dao;

import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionDao {

    void insert(Subscription subscription);

    Subscription select(Long id);

    List<Subscription> selectAll();

    List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType);

    boolean delete(Long id);

    boolean update(Subscription subscription);

    //clear all subscription_type for test only
    void clear();

    boolean checkIfUserSubscribed(Long id, Long id1);
}
