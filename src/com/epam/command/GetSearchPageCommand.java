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

public class GetSearchPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAccountPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;

    private static String magazinesPage;
    private static String errorPage;

    public GetSearchPageCommand() {
        //TODO THIS
        LOGGER.info("GET SEARCH PAGE COMMAND INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        magazinesPage = properties.getProperty("searchMagazinesPage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = errorPage;

        if (request.getParameter("q") != null && request.getParameter("p") != null &&
                request.getParameter("s") != null) {

            try {
                String query = request.getParameter("q");
                Integer pageNum = Integer.parseInt(request.getParameter("p"));
                Integer size = Integer.parseInt(request.getParameter("s"));

                //Page<Periodical> page = periodicalService.getPageByName(query, pageNum, size);

                request.setAttribute("categories", periodicalCategoryService.getAll());
                //request.setAttribute("page", page);
                request.setAttribute("query", query);

                resultPage = magazinesPage;
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("catId") + ", "
                        + request.getParameter("p") + ", "
                        + request.getParameter("s") + " to long");
            }
        }

        return resultPage;
    }
}
