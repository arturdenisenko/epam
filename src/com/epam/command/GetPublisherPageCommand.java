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
 * This class is for handle GET request in Publisher Page(view all periodicals in category)
 */

public class GetPublisherPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetPublisherPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;

    private static String periodicalsPage;
    private static String errorPage;

    public GetPublisherPageCommand() {
        LOGGER.info("GET PUBLISHER PAGE COMMAND INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        periodicalsPage = properties.getProperty("publisherPage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING PUBLISHER PAGE COMMAND");

        String resultPage = errorPage;

        if (request.getParameter("publisherId") != null && request.getParameter("p") != null &&
                request.getParameter("s") != null) {

            try {
                Long publisherId = Long.parseLong(request.getParameter("publisherId"));
                Integer pageNum = Integer.parseInt(request.getParameter("p"));
                Integer size = Integer.parseInt(request.getParameter("s"));

                Page<Periodical> page = periodicalService.getPageByPublisherId(pageNum, size, publisherId);

                request.setAttribute("categories", periodicalCategoryService.getAll());
                request.setAttribute("page", page);

                resultPage = periodicalsPage;
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {}, {}, {} to long",
                        request.getParameter("catId"), request.getParameter("p"), request.getParameter("s"));
            }
        }

        return resultPage;
    }
}
