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

package com.epam.command;

import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.SubscriptionDaoImpl;
import com.epam.dao.impl.SubscriptionTypeDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.periodical.Periodical;
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

/**
 * This class is for handle GET request in subscribePage
 */
public class GetSubscribePageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetSubscribePageCommand.class);

    private static PeriodicalService periodicalService;
    private static SubscriptionTypeService subscriptionTypeService;
    private static SubscriptionService subscriptionService;
    private static UserService userService;

    private static String subscribePage;
    private static String mainPage;
    private static String loginPage;
    private static String subscriptionSuccessPage;

    public GetSubscribePageCommand() {
        LOGGER.info("GET SUBSCRIBE PAGE COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        subscriptionTypeService = new SubscriptionTypeServiceImpl(SubscriptionTypeDaoImpl.getInstance());
        subscriptionService = new SubscriptionServiceImpl(SubscriptionDaoImpl.getInstance());
        userService = new UserServiceImpl(UserDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        subscribePage = properties.getProperty("subscribePage");
        mainPage = properties.getProperty("mainPage");
        loginPage = properties.getProperty("loginPage");
        subscriptionSuccessPage = properties.getProperty("subscriptionSuccessPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTE GET SUBSCRIBE COMMAND");
        String resultPage = mainPage;

        if (request.getSession().getAttribute("authenticated") == null &&
                request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = loginPage;
        } else if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                Periodical periodical = periodicalService.getPeriodicalById(id);

                if (periodical != null) {
                    request.setAttribute("periodical", periodical);
                    request.setAttribute("subscriptionTypes", subscriptionTypeService.getAll());
                    resultPage = subscribePage;
                } else {
                    LOGGER.info("Periodical with id {} doesn't exist", id);
                }
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {} to long", request.getParameter("id"));
            }
        }
        return resultPage;
    }
}
