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
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.util.GetPropertiesUtil;
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is for handle GET request in CategoryPage(view all periodicals in category)
 */
public class GetCategoryPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetCategoryPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;

    private static String periodicalCategoryPage;
    private static String errorPage;

    public GetCategoryPageCommand() {
        LOGGER.info("GET CATEGORY PAGE COMMAND INITIALIZATION");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        periodicalCategoryPage = properties.getProperty("categoryPage");
        errorPage = properties.getProperty("error404Page");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING CATEGORY PAGE COMMAND");

        String resultPage = errorPage;

        if (request.getParameter("catId") != null && request.getParameter("p") != null &&
                request.getParameter("s") != null) {

            try {
                /*ong categoryId = Long.parseLong(request.getParameter("catId"));
                Integer pageNum = Integer.parseInt(request.getParameter("p"));
                Integer size = Integer.parseInt(request.getParameter("s"));

                Category category = categoryService.findCategoryById(categoryId);
                Page<Magazine> page = magazineService.getPageByCategoryId(pageNum, size, category.getId());

                request.setAttribute("categories", categoryService.findAll());
                request.setAttribute("page", page);
                request.setAttribute("category", category);

                resultPage = categoryPage;*/

                Long categoryId = Long.parseLong(request.getParameter("catId"));
                Integer pageNum = Integer.parseInt(request.getParameter("p"));
                Integer size = Integer.parseInt(request.getParameter("s"));

                PeriodicalCategory category = periodicalCategoryService.getById(categoryId);
                Page<Periodical> page = (Page<Periodical>) periodicalService.getPageByCategory(pageNum, size, category.getId());

                request.setAttribute("categories", periodicalCategoryService.getAll());
                request.setAttribute("page", page);
                request.setAttribute("category", category);

                resultPage = periodicalCategoryPage;
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("catId") + ", "
                        + request.getParameter("p") + ", "
                        + request.getParameter("s") + " to long");
            }
        }

        return resultPage;
    }
}

