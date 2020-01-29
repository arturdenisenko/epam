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

package com.epam.command.admin.periodicalcategories;

import com.epam.command.ServletCommand;
import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCategoryAdminCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateCategoryAdminCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;

    private static String categoriesPage;
    private static String loginPage;

    public UpdateCategoryAdminCommand() {
        LOGGER.info("UPDATE CATEGORY ADMIN COMMAND INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        categoriesPage = properties.getProperty("adminCategoriesPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UPDATE CATEGORY ADMIN COMMAND EXECUTE");
        String resultPage = categoriesPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null && request.getParameter("name") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                PeriodicalCategory category = new PeriodicalCategory();
                category.setId(id);
                category.setName(request.getParameter("name"));
                periodicalCategoryService.update(category);

                request.setAttribute("updateSuccess", true);
                request.setAttribute("categories", periodicalCategoryService.getAll());
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {} to long", request.getParameter("id"));
            }

        }

        return resultPage;
    }

}
