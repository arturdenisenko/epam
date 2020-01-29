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

package com.epam.command.admin.users;

import com.epam.model.user.UserType;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAddAdminPageCommand.class);

    private static String addAdminPage;
    private static String loginPage;

    public GetAddAdminPageCommand() {
        LOGGER.info("GET ADD ADMIN PAGE INIT");

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        addAdminPage = properties.getProperty("adminAddAdminPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("GET ADD ADMIN PAGE EXECUTE");
        String resultPage = addAdminPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        }

        return resultPage;
    }
}
