/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command;

import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLoginPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetLoginPageCommand.class);

    private static String loginPage;
    private static String mainPage;

    public GetLoginPageCommand() {
        LOGGER.info("GET LOGIN PAGE COMMAND INITIALIZATION");

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        loginPage = properties.getProperty("loginPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING LOGIN COMMAND ");

        String resultPage = loginPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
        } else if (request.getParameter("email") == null && request.getParameter("password") == null) {
            LOGGER.info("Returning login page");
            return resultPage;
        }

        return resultPage;
    }
}
