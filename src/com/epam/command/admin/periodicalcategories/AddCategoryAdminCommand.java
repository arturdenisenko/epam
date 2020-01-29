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

package com.epam.command.admin.periodicalcategories;

import com.epam.command.ServletCommand;
import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCategoryAdminCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCategoryAdminCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;

    private static String addCategoryPage;
    private static String loginPage;

    public AddCategoryAdminCommand() {
        LOGGER.info("ADD PERIODICAL CATEGORY ADMIN COMMAND");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        addCategoryPage = properties.getProperty("adminAddCategoryPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTE ADD PERIODICAL CATEGORY ADMIN COMMAND");
        String resultPage = addCategoryPage;

        if (request.getParameter("name") != null) {
            PeriodicalCategory category = new PeriodicalCategory();
            category.setName(request.getParameter("name"));

            category = periodicalCategoryService.createPeriodicalCategory(category);

            request.setAttribute("success", category != null);
        }
        return resultPage;
    }
}
