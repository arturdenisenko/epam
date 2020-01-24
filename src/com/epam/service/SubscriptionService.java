/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.periodical.Periodical;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import com.epam.model.user.User;
import com.epam.util.Page;

import java.util.List;

public interface SubscriptionService {

    boolean checkSubscribe(User user, Periodical periodical);

    void create(Subscription subscription);

    Subscription getSubscriptionById(Long id);

    List<Subscription> getAll();

    List<Subscription> getUserSubscriptions(Long userId);

    List<Subscription> getBySubscriptionCategory(SubscriptionType subscriptionType);

    boolean delete(Long id);

    boolean update(Subscription subscription);

    Page<Subscription> getPage(Integer pageNum, Integer size);
}
