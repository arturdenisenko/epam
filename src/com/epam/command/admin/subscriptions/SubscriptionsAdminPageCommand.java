/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command.admin.subscriptions;

import com.epam.dao.impl.SubscriptionDaoImpl;
import com.epam.model.subscription.Subscription;
import com.epam.service.SubscriptionService;
import com.epam.service.impl.SubscriptionServiceImpl;
import com.epam.util.GetPropertiesUtil;
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscriptionsAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionsAdminPageCommand.class);

    private static SubscriptionService subscriptionService;

    private static String page;

    public SubscriptionsAdminPageCommand() {
        LOGGER.info("SUBSCRIPTION ADMIN PAGE COMMAND INIT");

        subscriptionService = new SubscriptionServiceImpl(SubscriptionDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        page = properties.getProperty("adminSubscriptionPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("SUBSCRIPTION ADMIN PAGE COMMAND EXECUTE");

        try {
            Integer pageNum = Integer.parseInt(request.getParameter("p"));
            Integer size = Integer.parseInt(request.getParameter("s"));

            Page<Subscription> page = subscriptionService.getPage(pageNum, size);

            request.setAttribute("page", page);
        } catch (NumberFormatException ex) {
            LOGGER.warn("COULDN'T PARSE {} , {} TO LONG", request.getParameter("p"), request.getParameter("s"));
        }

        return page;
    }

}
