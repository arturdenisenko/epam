/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.command.admin.periodicals;

import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.dao.impl.PublisherDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.periodical.Publisher;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.PublisherService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.service.impl.PublisherServiceImpl;
import com.epam.util.GetPropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddPeriodicalAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddPeriodicalAdminCommand.class);

    private static PeriodicalService periodicalService;
    private static PublisherService publisherService;
    private static PeriodicalCategoryService periodicalCategoryService;

    private static String addPeriodicalPage;
    private static String loginPage;

    public AddPeriodicalAdminCommand() {
        LOGGER.info("ADD MAGAZINE ADMIN COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());
        publisherService = new PublisherServiceImpl(PublisherDaoImpl.getInstance());
        periodicalCategoryService = new PeriodicalCategoryServiceImpl(PeriodicalCategoryDaoImpl.getInstance());
        imageService = new ImageServiceImpl(MysqlImageDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        addPeriodicalPage = properties.getProperty("adminAddMagazinePage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ADD MAGAZINE ADMIN COMMAND EXECUTE");
        String resultPage = addPeriodicalPage;

        if (request.getParameter("title") != null && request.getParameter("price") != null &&
                request.getParameter("publisher") != null && request.getParameter("category") != null &&
                request.getParameter("description") != null) {
            try {
                Part filePart = request.getPart("image");
                Long imageId = null;

                if (filePart != null && !Paths.get(filePart.getSubmittedFileName()).getFileName().toString().isEmpty()) {
                    InputStream image = filePart.getInputStream();
                    imageId = imageService.createImage(image);
                }

                PeriodicalCategory category = new PeriodicalCategory();
                category.setId(Long.parseLong(request.getParameter("category")));

                Publisher publisher = new Publisher();
                publisher.setId(Long.parseLong(request.getParameter("publisher")));

                Periodical periodical = new Periodical();
                periodical.setName(request.getParameter("title"));
                periodical.setAbout(request.getParameter("description"));
                periodical.setCostPerMonth(Float.parseFloat(request.getParameter("price")));
                periodical.setPeriodicalCategory(category);
                periodical.setPublisher(publisher);
                periodical.setImageLink(imageId)
                periodical.setActive(request.getParameter("enabled") != null)

                periodicalService.createPeriodical(periodical);

                request.setAttribute("publishers", publisherService.getAll());
                request.setAttribute("categories", periodicalCategoryService.getAll());
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse {}, {}, {} to long",
                        request.getParameter("id"), request.getParameter("category"), request.getParameter("publisher"));
            } catch (ServletException | IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }

        return resultPage;
    }

}
