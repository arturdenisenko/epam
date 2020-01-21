/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command.admin.periodicals;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PublisherService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddPeriodicalAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAddPeriodicalAdminPageCommand.class);

    private static PublisherService publisherService;
    private static PeriodicalCategoryService periodicalCategoryService;

    private static String addMagazinePage;
    private static String loginPage;

    public GetAddPeriodicalAdminPageCommand() {
        LOGGER.info("GET ADD PERIODICAL ADMIN COMMAND INIT");

        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());
        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        addMagazinePage = properties.getProperty("adminAddPeriodicalPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("GET ADD PERIODICAL ADMIN COMMAND EXECUTE");
        String resultPage = addMagazinePage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        } else {
            request.setAttribute("publishers", publisherService.getAll());
            request.setAttribute("categories", periodicalCategoryService.getAll());
        }

        return resultPage;
    }
}