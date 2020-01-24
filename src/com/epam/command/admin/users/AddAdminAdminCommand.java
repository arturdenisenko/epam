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

import com.epam.dao.impl.UserDaoImpl;
import com.epam.model.user.User;
import com.epam.model.user.UserType;
import com.epam.service.UserService;
import com.epam.service.impl.UserServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAdminAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddAdminAdminCommand.class);

    private static UserService userService;

    private static String addAdminPage;

    public AddAdminAdminCommand() {
        LOGGER.info("ADD ADMIN ADMIN COMMAND INIT");

        userService = new UserServiceImpl(UserDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        addAdminPage = properties.getProperty("adminAddAdminPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ADD ADMIN ADMIN COMMAND INIT EXECUTE");
        String resultPage = addAdminPage;

        if (request.getParameter("fname") != null && request.getParameter("lname") != null &&
                request.getParameter("email") != null && request.getParameter("password") != null &&
                userService.checkEmailAvailability(request.getParameter("email"))) {

            User user = new User();
            user.setFirstName(request.getParameter("fname"));
            user.setLastName(request.getParameter("lname"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setAddress(request.getParameter(""));
            user.setBalance(0F);
            user.setUserType(UserType.ADMIN);

            request.setAttribute("success", userService.create(user));
        }

        return resultPage;
    }
}
