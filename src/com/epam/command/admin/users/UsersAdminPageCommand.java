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
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsersAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersAdminPageCommand.class);

    private static UserService userService;

    private static String page;

    public UsersAdminPageCommand() {
        LOGGER.info("USER ADMIN PAGE COMMAND INIT");

        userService = new UserServiceImpl(UserDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        page = properties.getProperty("adminUsersPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("USER ADMIN PAGE COMMAND EXECUTE");

        try {
            Integer pageNum = Integer.parseInt(request.getParameter("p"));
            Integer size = Integer.parseInt(request.getParameter("s"));

            Page<User> page = userService.getAllByUsersType(pageNum, size, UserType.USER);

            request.setAttribute("page", page);
        } catch (NumberFormatException ex) {
            LOGGER.info("Couldn't parse " + request.getParameter("p") + ", "
                    + request.getParameter("s") + " to long");
        }

        return page;
    }
}
