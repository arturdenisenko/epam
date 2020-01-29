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

import com.epam.dao.impl.SubscriptionDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.subscription.Subscription;
import com.epam.model.user.User;
import com.epam.service.SubscriptionService;
import com.epam.service.UserService;
import com.epam.service.impl.SubscriptionServiceImpl;
import com.epam.service.impl.UserServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This class if used for handle GET request in accountPage
 */
public class GetAccountPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAccountPageCommand.class);

    private static UserService userService;
    private static SubscriptionService subscriptionService;

    private static String mainPage;
    private static String accountPage;

    public GetAccountPageCommand() {
        LOGGER.info("GET ACCOUNT PAGE INIT");

        userService = new UserServiceImpl(UserDaoImpl.getInstance());
        subscriptionService = new SubscriptionServiceImpl(SubscriptionDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        mainPage = properties.getProperty("mainPage");
        accountPage = properties.getProperty("accountPage");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = mainPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true)) {

            User user = userService.getByEmail(request.getSession().getAttribute("email").toString());
            List<Subscription> subscriptions = subscriptionService.getUserSubscriptions(user.getId());

            request.setAttribute("user", user);
            request.setAttribute("isSubscriptionsEmpty", subscriptions.isEmpty());
            request.setAttribute("subscriptions", subscriptions);

            resultPage = accountPage;
        }

        return resultPage;
    }
}
