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
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCategoryAdminCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteCategoryAdminCommand.class);

    private static PeriodicalCategoryService categoryService;

    private static String categoriesPage;
    private static String loginPage;

    public DeleteCategoryAdminCommand() {
        LOGGER.info("DELETE CATEGORY ADMIN COMMAND INIT");

        categoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        categoriesPage = properties.getProperty("adminCategoriesPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("DELETE CATEGORY ADMIN COMMAND EXECUTE");
        String resultPage = categoriesPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                request.setAttribute("deletionSuccess", categoryService.deleteById(id));
                request.setAttribute("categories", categoryService.getAll());
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {} to long", request.getParameter("id"));
            }
        }

        return resultPage;
    }

}
