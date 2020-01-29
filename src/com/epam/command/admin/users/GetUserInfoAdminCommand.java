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

package com.epam.command.admin.users;

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

public class GetUserInfoAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetUserInfoAdminCommand.class);

    private static UserService userService;
    private static SubscriptionService subscriptionService;

    private static String usersPage;
    private static String userInfoPage;

    public GetUserInfoAdminCommand() {
        LOGGER.info("GET USER INFO COMMAND INIT");

        userService = new UserServiceImpl(UserDaoImpl.getInstance());
        subscriptionService = new SubscriptionServiceImpl(SubscriptionDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        usersPage = properties.getProperty("adminUsersPage");
        userInfoPage = properties.getProperty("adminUserInfoPage");
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");
        String resultPage = usersPage;

        if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                User user = userService.getById(id);

                if (user != null) {
                    List<Subscription> subscriptions = subscriptionService.getUserSubscriptions(user.getId());

                    request.setAttribute("user", user);
                    request.setAttribute("isSubscriptionsEmpty", subscriptions.isEmpty());
                    request.setAttribute("subscriptions", subscriptions);
                    resultPage = userInfoPage;
                }
            } catch (NumberFormatException ex) {
                LOGGER.warn("COULDN'T PARSE ID {} TO LONG", request.getParameter("p"));
            }
        }
        return resultPage;
    }
}
