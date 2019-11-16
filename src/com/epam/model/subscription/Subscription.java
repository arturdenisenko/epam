package com.epam.model.subscription;

import com.epam.model.periodical.Periodical;
import com.epam.model.user.User;

import java.util.Date;

public class Subscription {
    private int id;
    private User user;
    private Periodical periodical;
    private SubscriptionType type;
    private Date startDate;
    private Date endDate;
    private double Price;
}
