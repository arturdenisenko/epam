/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.periodical.Periodical;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import com.epam.model.user.User;

import java.util.List;

public interface SubscriptionService {

    boolean checkSubscribe(User user, Periodical periodical);

    void create(Subscription subscription);

    Subscription getByid(Long id);

    List<Subscription> selectAll();

    List<Subscription> getUserSubscriptions(Long userId);

    List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType);

    boolean delete(Long id);

    boolean update(Subscription subscription);

}
