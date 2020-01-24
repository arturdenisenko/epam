/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

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
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

public class SubscriptionServiceImpl implements SubscriptionService {
   //TODO THIS LOGIC
    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private SubscriptionDao subscriptionDao;

    public SubscriptionServiceImpl(SubscriptionDao subscriptionDao) {
        LOGGER.info("SUBSCRIPTION SERVICE IMPL INIT");
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public boolean checkSubscribe(User user, Periodical periodical) {
        LOGGER.info("Checking if user is subscribed to periodical");

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
    public Subscription getSubscriptionById(Long id) {
        LOGGER.info("Finding subscription by id " + id);

        if (id == null) {
            return null;
        }

        return subscriptionDao.select(id);
    }

    @Override
    public List<Subscription> getAll() {
        LOGGER.info("GET ALL SUBSCRIPTIONS");
        return subscriptionDao.selectAll();
    }

    @Override
    public List<Subscription> getUserSubscriptions(Long userId) {
        LOGGER.info("Finding subscription by id " + userId);
        if (userId == null) {
            return null;
        }
        return subscriptionDao.getUserSubscriptions(userId);
    }

    @Override
    public List<Subscription> getBySubscriptionCategory(SubscriptionType subscriptionType) {
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

    @Override
    public Page<Subscription> getPage(Integer page, Integer size) {
        LOGGER.info("Getting page number {}, of size {}", page, size);

        if (page == null || size == null || page < 1 || size < 1) {
            return null;
        }

        List<Subscription> items = subscriptionDao.selectPage((page - 1) * size, size);
        return new Page(items, page, size);
    }
}
