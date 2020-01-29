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
import com.epam.model.user.UserType;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCategoryAdminPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddCategoryAdminPageCommand.class);

    private static String addCategoryPage;
    private static String loginPage;

    public AddCategoryAdminPageCommand() {
        LOGGER.info("ADD CATEGORY ADMIN PAGE COMMAND INIT");

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        addCategoryPage = properties.getProperty("adminAddCategoryPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ADD CATEGORY ADMIN PAGE COMMAND EXECUTE");
        String resultPage = addCategoryPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        }

        return resultPage;
    }
}
