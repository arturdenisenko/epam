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
import com.epam.service.PublisherService;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to handle GET requests to the admin page to view all publishers.
 */
public class PublishersAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PublishersAdminPageCommand.class);

    private static PublisherService publisherService;

    private static String page;

    public PublishersAdminPageCommand() {
        LOGGER.info("PUBLISHER ADMIN PAGE COMMAND INIT");

        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        page = properties.getProperty("adminPublishersPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("PUBLISHER ADMIN PAGE COMMAND EXECUTE");

        request.setAttribute("publishers", publisherService.getAll());

        return page;
    }
}
