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

/*
 * @Denisenko Artur
 */

package com.epam.command;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used for handle GET request in searchPeriodicals page
 */
public class GetSearchPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetSearchPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;

    private static String periodicalsPage;
    private static String errorPage;

    public GetSearchPageCommand() {
        LOGGER.info("GET SEARCH PAGE COMMAND INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        periodicalsPage = properties.getProperty("searchPeriodicalsPage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING SEARCH PAGE COMMAND");

        String resultPage = errorPage;

        if (request.getParameter("q") != null && request.getParameter("p") != null &&
                request.getParameter("s") != null) {

            try {
                String query = request.getParameter("q");
                Integer pageNum = Integer.parseInt(request.getParameter("p"));
                Integer size = Integer.parseInt(request.getParameter("s"));

                Page<Periodical> page = periodicalService.getPageByNameQuery(query, pageNum, size);

                request.setAttribute("categories", periodicalCategoryService.getAll());
                request.setAttribute("page", page);
                request.setAttribute("query", query);

                resultPage = periodicalsPage;
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {}, {}, {} to long",
                        request.getParameter("catId"), request.getParameter("p"), request.getParameter("s"));
            }
        }

        return resultPage;
    }
}
