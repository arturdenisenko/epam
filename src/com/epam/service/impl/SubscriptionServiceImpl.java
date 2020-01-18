/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.SubscriptionDao;
import com.epam.model.periodical.Periodical;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import com.epam.model.user.User;
import com.epam.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class SubscriptionServiceImpl implements SubscriptionService {
   //TODO THIS LOGIC
    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private SubscriptionDao subscriptionDao;

    public SubscriptionServiceImpl(SubscriptionDao subscriptionDao) {
        LOGGER.info("Initializing SubscriptionServiceImpl");

        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public boolean checkSubscribe(User user, Periodical periodical) {
        LOGGER.info("Checking if user is subscribed to magazine");

        if (user == null || periodical == null) {
            return false;
        }

        return subscriptionDao.checkIfUserSubscribed(user.getId(), periodical.getId());
    }

    @Override
    public void create(Subscription subscription) {
        LOGGER.info("Creating new subscription");
        Objects.requireNonNull(subscription);
        subscriptionDao.insert(subscription);
    }

    @Override
    public Subscription getByid(Long id) {
        return null;
    }

    @Override
    public List<Subscription> selectAll() {
        return null;
    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        return null;
    }

    @Override
    public List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Subscription subscription) {
        return false;
    }
}
