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
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is for handle GET request in logout
 */
public class LogoutCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;

    private static String mainPage;

    public LogoutCommand() {
        LOGGER.info("LOGOUT COMMAND INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTE LOGOUT COMMAND");
        LOGGER.info("LOGGING OUT USER {}", request.getSession().getAttribute("email"));

        request.getSession().invalidate();

        request.setAttribute("categories", periodicalCategoryService.getAll());
        request.setAttribute("latestPeriodicals", periodicalService.getLastAdded(6));

        return mainPage;
    }
}
