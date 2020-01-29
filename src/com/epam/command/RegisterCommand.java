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

package com.epam.command;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.user.User;
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.UserService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.service.impl.UserServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterCommand.class);

    private static UserService userService;
    private static PeriodicalCategoryService categoryService;
    private static PeriodicalService periodicalService;

    private static String registerPage;
    private static String mainPage;

    public RegisterCommand() {
        LOGGER.info("REGISTER COMMAND INITIALIZATION");

        userService = new UserServiceImpl(UserDaoImpl.getInstance());
        categoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        registerPage = properties.getProperty("registerPage");
        mainPage = properties.getProperty("mainPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("EXECUTING REGISTER COMMAND");

        String resultPage = registerPage;

        if (request.getParameter("fname") != null && request.getParameter("lname") != null &&
                request.getParameter("email") != null && request.getParameter("password") != null &&
                request.getParameter("address") != null &&
                userService.checkEmailAvailability(request.getParameter("email"))) {

            LOGGER.info("NEW USER REGISTRATION");

            User user = new User();
            user.setFirstName(request.getParameter("fname"));
            user.setLastName(request.getParameter("lname"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setAddress(request.getParameter("address"));
            user.setBalance((float) 0);
            user.setUserType(UserType.USER);

            if (userService.create(user)) {
                request.setAttribute("categories", categoryService.getAll());
                request.setAttribute("periodicals", periodicalService.getAll());

                resultPage = mainPage;
            }
        }

        return resultPage;
    }
}
