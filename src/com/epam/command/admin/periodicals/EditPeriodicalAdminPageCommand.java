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

package com.epam.command.admin.periodicals;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.PublisherService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditPeriodicalAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditPeriodicalAdminPageCommand.class);

    private static PeriodicalService periodicalService;
    private static PublisherService publisherService;
    private static PeriodicalCategoryService periodicalCategoryService;

    private static String editPeriodicalPage;
    private static String periodicalPage;
    private static String loginPage;

    public EditPeriodicalAdminPageCommand() {
        LOGGER.info("EDIT PERIODICAL ADMIN PAGE COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());
        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        editPeriodicalPage = properties.getProperty("adminEditPeriodicalPage");
        periodicalPage = properties.getProperty("adminPeriodicalsPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EDIT PERIODICAL ADMIN PAGE COMMAND EXECUTE");
        String resultPage = editPeriodicalPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Periodical periodical = periodicalService.getPeriodicalById(id);

                if (periodical != null) {
                    request.setAttribute("periodical", periodical);
                    request.setAttribute("categories", periodicalCategoryService.getAll());
                    request.setAttribute("publishers", publisherService.getAll());
                } else {
                    resultPage = periodicalPage;
                }
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {}} to long", request.getParameter("id"));
            }

        }

        return resultPage;
    }
}
