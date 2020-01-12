/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.user.User;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.UserService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.service.impl.UserServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutCommand.class);

    private static UserService userService;
    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalService periodicalService;

    private static String loginPage;
    private static String mainPage;

    public LoginCommand() {
        LOGGER.info("Initializing LoginCommand");

        userService = new UserServiceImpl(UserDaoImpl.getInstance());
        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        loginPage = properties.getProperty("loginPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Executing command");

        String resultPage = loginPage;

        if (request.getParameter("email") != null) {
            User user = userService.getByEmail(request.getParameter("email"));

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("email", user.getEmail());
                session.setAttribute("username", user.getFirstName() + " " + user.getLastName());
                session.setAttribute("authenticated", true);
                session.setAttribute("role", user.getUserType().name());

                request.setAttribute("categories", periodicalCategoryService.getAll());
                request.setAttribute("latestMagazines", periodicalService.getAll());

                resultPage = mainPage;
            } else {
                request.setAttribute("loginSuccess", false);
            }
        }

        return resultPage;
    }
}
