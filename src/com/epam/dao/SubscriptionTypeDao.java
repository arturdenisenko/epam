package com.epam.dao;

import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeDao {

    void insert(SubscriptionType subscriptionType);

    SubscriptionType select(int id);

    List<SubscriptionType> selectAll();

    boolean delete(int id);

    boolean update(SubscriptionType subscriptionType);

    //clear all subscription_type for test only
    void clear();
}
