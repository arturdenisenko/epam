package com.epam.dao;

import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionDao {
    //TODO Написать ДАО, и тесты
    List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType);
}
