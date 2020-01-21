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

/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command;

import com.epam.command.admin.periodicalcategories.*;
import com.epam.command.admin.periodicals.*;
import com.epam.command.admin.publishers.*;
import com.epam.command.admin.users.*;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * This class if for working with servlet commands
 */
public class CommandManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);

    private static String errorPage;
    private HashMap<String, ServletCommand> getCommands;
    private HashMap<String, ServletCommand> postCommands;

    public CommandManager() {
        LOGGER.info("COMMAND MANAGER INITIALIZING");

        getCommands = new HashMap<>();
        postCommands = new HashMap<>();

        /**
         * GET COMMANDS HERE
         */

        getCommands.put("/", new GetMainPageCommand());
        getCommands.put("/login", new GetLoginPageCommand());
        getCommands.put("/logout", new LogoutCommand());
        getCommands.put("/register", new GetRegisterPageCommand());
        getCommands.put("/periodical", new GetPeriodicalPageCommand());
        getCommands.put("/category", new GetCategoryPageCommand());
        getCommands.put("/subscribe", new GetSubscribePageCommand());
        getCommands.put("/account", new GetAccountPageCommand());
        getCommands.put("/search", new GetSearchPageCommand());

        /**
         * GET COMMANDS FOR ADMINS
         */

        //admin categories
        getCommands.put("/admin/categories", new CategoriesAdminPageCommand());
        getCommands.put("/admin/categories/add", new AddCategoryAdminPageCommand());
        getCommands.put("/admin/categories/delete", new DeleteCategoryAdminCommand());
        getCommands.put("/admin/categories/edit", new EditCategoryAdminPageCommand());

        //admin publishers
        getCommands.put("/admin/publishers", new PublishersAdminPageCommand());
        getCommands.put("/admin/publishers/edit", new EditPublisherAdminPageCommand());
        getCommands.put("/admin/publishers/add", new AddPublisherAdminPageCommand());
        getCommands.put("/admin/publishers/delete", new DeletePublisherAdminCommand());

        //admin users
        getCommands.put("/admin/users", new UsersAdminPageCommand());
        getCommands.put("/admin/admins", new AdminsAdminPageCommand());
        getCommands.put("/admin/admins/add", new GetAddAdminPageCommand());
        getCommands.put("/admin/user", new GetUserInfoAdminCommand());

        //admin periodicals
        getCommands.put("/admin/periodicals", new PeriodicalsAdminPageCommand());
        getCommands.put("/admin/periodicals/add", new GetAddPeriodicalAdminPageCommand());
        getCommands.put("/admin/periodicals/edit", new EditPeriodicalAdminPageCommand());
        getCommands.put("/admin/periodicals/delete", new DeletePeriodicalAdminCommand());

        //admin subscriptions
        //getCommands.put("/admin/subscriptions", new SubscriptionsAdminPageCommand());

        //===================POST commands===================

        postCommands.put("/login", new LoginCommand());
        postCommands.put("/register", new RegisterCommand());
        postCommands.put("/subscribe", new SubscribeCommand());

        //admin categories

        postCommands.put("/admin/categories/add", new AddCategoryAdminCommand());
        postCommands.put("/admin/categories/update", new UpdateCategoryAdminCommand());

        //admin publishers

        postCommands.put("/admin/publishers/add", new AddPublisherAdminCommand());
        postCommands.put("/admin/publishers/update", new UpdatePublisherAdminCommand());

        //admin users
        postCommands.put("/admin/admins/add", new AddAdminAdminCommand());

        //admin periodicals
        postCommands.put("/admin/periodicals/add", new AddPeriodicalAdminCommand());
        //postCommands.put("/admin/periodicals/update", new UpdatePeriodicalAdminCommand());


        GetPropertiesUtil properties = GetPropertiesUtil.getInstance().getInstance();
        errorPage = properties.getProperty("errorPage");
    }

    /**
     * This method is used to get a command instance mapped to http get method, based on a request.
     *
     * @param request http request from servlet.
     * @return A servlet command instance.
     */
    public ServletCommand getGetCommand(HttpServletRequest request) {
        String command = getMapping(request);
        LOGGER.info("GETTING COMMAND " + command);

        if (getCommands.get(command) == null) {
            return getCommands.get("/");
        }

        return getCommands.get(command);
    }

    /**
     * This method is used to get a command instance mapped to http post method, based on a request.
     *
     * @param request http request from servlet.
     * @return A servlet command instance.
     */
    public ServletCommand getPostCommand(HttpServletRequest request) {
        String command = getMapping(request);
        LOGGER.info("GETTING COMMAND " + command);

        if (postCommands.get(command) == null) {
            return getCommands.get("/");
        }

        return postCommands.get(command);
    }

    /**
     * This is a helper method to get command mapping from uri.
     *
     * @param request http request from servlet.
     * @return Command mapping.
     */
    public String getMapping(HttpServletRequest request) {
        String mapping = request.getRequestURI().substring(request.getContextPath().length());
        if (mapping.endsWith("/")) {
            mapping = mapping.substring(0, mapping.length() - 1);
        }

        return mapping;
    }
}
