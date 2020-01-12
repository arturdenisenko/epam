/*
 * @Denisenko Artur
 */

package com.epam.filters;

import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;

import java.util.ArrayList;
import java.util.List;

public class SubscriptionSelectByCategoryFilter implements ModelFilter<Subscription, SubscriptionType> {
    @Override
    public List<Subscription> meetCriteria(List<Subscription> subscriptionList, SubscriptionType subscriptionType) {
        List<Subscription> subscriptions = new ArrayList<>();
        for (Subscription subscription : subscriptionList) {
            if (subscription.getType().equals(subscriptionType)) {
                subscriptions.add(subscription);
            }
        }
        return subscriptions;
    }
}
