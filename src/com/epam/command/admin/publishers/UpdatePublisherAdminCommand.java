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

package com.epam.command.admin.publishers;

import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.periodical.Publisher;
import com.epam.model.user.UserType;
import com.epam.service.PublisherService;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePublisherAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdatePublisherAdminCommand.class);

    private static PublisherService publisherService;

    private static String publishersPage;
    private static String loginPage;

    public UpdatePublisherAdminCommand() {
        LOGGER.info("UPDATE PUBLISHER ADMIN COMMAND INIT");

        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        publishersPage = properties.getProperty("adminPublishersPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UPDATE PUBLISHER ADMIN COMMAND INIT EXECUTE");
        String resultPage = publishersPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null && request.getParameter("title") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                Publisher publisher = new Publisher();
                publisher.setId(id);
                publisher.setName(request.getParameter("title"));
                publisherService.update(publisher);

                request.setAttribute("updateSuccess", true);
                request.setAttribute("publishers", publisherService.getAll());
            } catch (NumberFormatException ex) {
                LOGGER.warn("Couldn't parse {} to long", request.getParameter("id"));
            }
        }
        return resultPage;
    }
}
