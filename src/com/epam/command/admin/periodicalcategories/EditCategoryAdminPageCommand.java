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

public class EditCategoryAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(EditCategoryAdminPageCommand.class);

    private static PeriodicalCategoryService periodicalCategoryService;

    private static String editCategoryPage;
    private static String categoriesPage;
    private static String loginPage;

    public EditCategoryAdminPageCommand() {
        LOGGER.info("EDIT CATEGORY ADMIN PAGE INIT");

        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        editCategoryPage = properties.getProperty("adminEditCategoryPage");
        categoriesPage = properties.getProperty("adminCategoriesPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EDIT CATEGORY ADMIN PAGE EXECUTE");
        String resultPage = editCategoryPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                PeriodicalCategory category = periodicalCategoryService.getById(id);

                if (category != null) {
                    request.setAttribute("category", category);
                } else {
                    resultPage = categoriesPage;
                }
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {} to long", request.getParameter("id"));
            }
        }

        return resultPage;
    }
}
