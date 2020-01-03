package com.epam.dao;

import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeDao {

    void insert(SubscriptionType subscriptionType);

    SubscriptionType select(Long id);

    List<SubscriptionType> selectAll();

    boolean delete(Long id);

    boolean update(SubscriptionType subscriptionType);

    //clear all subscription_type for test only
    void clear();
}
