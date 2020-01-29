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


package com.epam.command.admin.periodicalcategories;

import com.epam.command.ServletCommand;
import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoriesAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoriesAdminPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;

    private static String page;

    public CategoriesAdminPageCommand() {
        LOGGER.info("CATEGORIES IN ADMIN PAGE COMMAND INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        page = properties.getProperty("adminCategoriesPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTE ADMIN PAGE CATEGORIES COMMAND");

        request.setAttribute("categories", periodicalCategoryService.getAll());

        return page;
    }

}
