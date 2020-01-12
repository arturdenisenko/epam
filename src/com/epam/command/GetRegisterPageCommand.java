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

public class GetRegisterPageCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetRegisterPageCommand.class);

    private static String registerPage;
    private static String mainPage;

    public GetRegisterPageCommand() {
        LOGGER.info("GET REGISTER PAGE COMMAND INITIALIZING");

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        registerPage = properties.getProperty("registerPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING GET REGISTER PAGE COMMAND");

        String resultPage = registerPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true)) {
            resultPage = mainPage;
            //TODO --- this fname and lname
        } else if (request.getParameter("fname") == null && request.getParameter("lname") == null &&
                request.getParameter("email") == null && request.getParameter("password") == null &&
                request.getParameter("address") == null) {
            LOGGER.info("RETURNING REGISTRATION PAGE");
            return resultPage;
        }

        return resultPage;
    }
}
