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

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.SubscriptionDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.user.User;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.SubscriptionService;
import com.epam.service.UserService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.service.impl.SubscriptionServiceImpl;
import com.epam.service.impl.UserServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetPeriodicalPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetPeriodicalPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;
    private static UserService userService;
    private static SubscriptionService subscriptionService;

    private static String magazinePage;
    private static String errorPage;

    public GetPeriodicalPageCommand() {
        LOGGER.info("Get Periodical Page Command Initializing");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        userService = new UserServiceImpl(UserDaoImpl.getInstance());
        subscriptionService = new SubscriptionServiceImpl(SubscriptionDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        magazinePage = properties.getProperty("periodicalPage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing periodical page command");
        String resultPage = errorPage;

        if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                Periodical periodical = periodicalService.getById(id);

                if (periodical != null) {
                    if (request.getSession().getAttribute("authenticated") != null &&
                            request.getSession().getAttribute("authenticated").equals(true)) {
                        User user = userService.getByEmail(request.getSession().getAttribute("email").toString());
                        boolean isSubscribed = subscriptionService.checkSubscribe(user, periodical);

                        request.setAttribute("isSubscribed", isSubscribed);
                    }

                    request.setAttribute("categories", periodicalCategoryService.getAll());
                    request.setAttribute("periodical", periodical);

                    resultPage = magazinePage;
                } else {
                    LOGGER.info("Magazine with id " + id + " doesn't exist");
                }
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }
        }

        return resultPage;
    }

}
