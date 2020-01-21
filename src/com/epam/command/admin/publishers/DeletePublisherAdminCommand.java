/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command.admin.publishers;

import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.user.UserType;
import com.epam.service.PublisherService;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePublisherAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddPublisherAdminPageCommand.class);

    private static PublisherService publisherService;

    private static String publishersPage;
    private static String loginPage;

    public DeletePublisherAdminCommand() {
        LOGGER.info("DELETE PUBLISHERS PAGE COMMAND INIT");

        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        publishersPage = properties.getProperty("adminPublishersPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("DELETE PUBLISHERS PAGE COMMAND EXECUTE");
        String resultPage = publishersPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("User not authorized");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                request.setAttribute("deletionSuccess", publisherService.deletePublisherById(id));
                request.setAttribute("publishers", publisherService.getAll());
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse " + request.getParameter("id") + " to long");
            }
        }

        return resultPage;
    }
}
