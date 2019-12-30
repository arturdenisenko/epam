package com.epam.dao;

import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionDao {
    //TODO Написать ДАО, и тесты

    void insert(Subscription subscription);

    Subscription select(int id);

    List<Subscription> selectAll();

    List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType);

    boolean delete(int id);

    boolean update(Subscription subscription);

    //clear all subscription_type for test only
    void clear();
}
