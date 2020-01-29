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

package com.epam.command;

import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.SubscriptionDaoImpl;
import com.epam.dao.impl.SubscriptionTypeDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import com.epam.model.user.User;
import com.epam.service.PeriodicalService;
import com.epam.service.SubscriptionService;
import com.epam.service.SubscriptionTypeService;
import com.epam.service.UserService;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.service.impl.SubscriptionServiceImpl;
import com.epam.service.impl.SubscriptionTypeServiceImpl;
import com.epam.service.impl.UserServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

public class SubscribeCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscribeCommand.class);

    private static PeriodicalService periodicalService;
    private static SubscriptionTypeService subscriptionTypeService;
    private static SubscriptionService subscriptionService;
    private static UserService userService;

    private static String mainPage;
    private static String subscriptionSuccessPage;

    public SubscribeCommand() {
        LOGGER.info("SUBSCRIBE COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        subscriptionTypeService = new SubscriptionTypeServiceImpl(SubscriptionTypeDaoImpl.getInstance());
        subscriptionService = new SubscriptionServiceImpl(SubscriptionDaoImpl.getInstance());
        userService = new UserServiceImpl(UserDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        mainPage = properties.getProperty("mainPage");
        subscriptionSuccessPage = properties.getProperty("subscriptionSuccessPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING SUBSCRIBE COMMAND");
        String resultPage = mainPage;

        if (request.getParameter("id") != null && request.getParameter("type") != null) {
            try {
                Long subTypeId = Long.parseLong(request.getParameter("type"));
                Long magazineId = Long.parseLong(request.getParameter("id"));

                SubscriptionType subscriptionType = subscriptionTypeService.getSubscriptionTypeById(subTypeId);
                Periodical periodical = periodicalService.getPeriodicalById(magazineId);
                User user = userService.getByEmail(request.getSession().getAttribute("email").toString());

                if (periodical != null && periodical.isActive() && subscriptionType != null) {
                    Subscription subscription = new Subscription();

                    subscription.setPeriodical(periodical);
                    subscription.setType(subscriptionType);
                    subscription.setCost(periodical.getCostPerMonth() * subscriptionType.getPriceMultiplier());
                    subscription.setUser(user);

                    LocalDate startDate = LocalDate.now();
                    LocalDate endDate = startDate.plusMonths(subscriptionType.getDurationByMonth());

                    subscription.setStartDate(startDate);
                    subscription.setEndDate(endDate);

                    subscriptionService.create(subscription);

                    resultPage = subscriptionSuccessPage;
                } else {
                    LOGGER.info("Couldn't find periodical or subscriptionType by ids "
                            + periodical
                            + ", "
                            + subTypeId);
                }

            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id")
                        + ", "
                        + request.getParameter("type")
                        + " to long");
            }
        }

        return resultPage;
    }
}
