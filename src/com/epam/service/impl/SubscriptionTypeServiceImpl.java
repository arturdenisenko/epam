/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.service.impl;

import com.epam.dao.SubscriptionTypeDao;
import com.epam.model.subscription.SubscriptionType;
import com.epam.service.SubscriptionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionTypeServiceImpl.class);

    private SubscriptionTypeDao subscriptionTypeDao;

    public SubscriptionTypeServiceImpl(SubscriptionTypeDao subscriptionDao) {
        LOGGER.info("SUBSCRIPTION TYPE SERVICE INITIALIZATION");

        this.subscriptionTypeDao = subscriptionDao;
    }

    @Override
    public List<SubscriptionType> getAll() {
        LOGGER.info("GET ALL SUBSCRIPTION TYPES");

        return subscriptionTypeDao.selectAll();
    }

    @Override
    public SubscriptionType getSubscriptionTypeById(Long id) {
        LOGGER.info("GET SUBS TYPE BY ID " + id);

        if (id == null) {
            return null;
        }

        return subscriptionTypeDao.select(id);
    }
}
