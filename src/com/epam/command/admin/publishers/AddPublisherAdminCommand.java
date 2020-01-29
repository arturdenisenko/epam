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

package com.epam.command.admin.publishers;

import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.periodical.Publisher;
import com.epam.service.PublisherService;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddPublisherAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddPublisherAdminCommand.class);

    private static PublisherService publisherService;

    private static String addPublisherPage;

    public AddPublisherAdminCommand() {
        LOGGER.info("ADD PUBLISHER ADMIN COMMAND INIT");

        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        addPublisherPage = properties.getProperty("adminAddPublisherPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ADD PUBLISHER ADMIN COMMAND EXECUTE");
        String resultPage = addPublisherPage;

        if (request.getParameter("title") != null) {
            Publisher publisher = new Publisher();
            publisher.setName(request.getParameter("title"));

            publisher = publisherService.createPublisher(publisher);

            request.setAttribute("success", publisher != null);
        }

        return resultPage;
    }
}
