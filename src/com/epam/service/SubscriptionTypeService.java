/*
 * @Denisenko Artur
 */

package com.epam.service;

import com.epam.model.subscription.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {
    //Return all subscriprion types by id
    List<SubscriptionType> getAll();

    //get SubscriptionTypeById
    SubscriptionType getSubscriptionTypeById(Long id);
}
