package com.epam.dao.impl;

import com.epam.dao.SubscriptionDao;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public class SubscriptionDaoImpl implements SubscriptionDao {
    @Override
    public List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType) {
        return null;
    }
   /* @Override
    public List<Subscription> selectBySubscriptionCategory(SubscriptionType subscriptionType) {
            LOGGER.info("SELECT Periodical by SubscriptionType {}", subscriptionType.toString());
            Filter filter = new SubscriptionSelectByCategoryFilter();
            return filter.meetCriteria(this.selectAll(), subscriptionType);
        }
    }*/
}
