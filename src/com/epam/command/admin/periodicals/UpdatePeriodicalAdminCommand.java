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

package com.epam.command.admin.periodicals;

import com.epam.command.ServletCommand;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.periodical.Publisher;
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePeriodicalAdminCommand implements ServletCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicalsAdminPageCommand.class);

    static PeriodicalService periodicalService;

    private static String periodicalsPage;
    private static String loginPage;

    public UpdatePeriodicalAdminCommand() {
        LOGGER.info("UPDATE PERIODICAL COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        periodicalsPage = properties.getProperty("adminPeriodicalsPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("UPDATE PERIODICAL COMMAND EXECUTE");
        String resultPage = periodicalsPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null && request.getParameter("title") != null &&
                request.getParameter("price") != null && request.getParameter("publisher") != null &&
                request.getParameter("category") != null && request.getParameter("description") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));
                Periodical existsPeriodical = periodicalService.getPeriodicalById(id);//For image check only

                //get category
                PeriodicalCategory category = new PeriodicalCategory();
                category.setId(Long.parseLong(request.getParameter("category")));

                //get publisher
                Publisher publisher = new Publisher();
                publisher.setId(Long.parseLong(request.getParameter("publisher")));

                //get image
                /*Part filePart = request.getPart("image");
                Long imageId = null;

                if (filePart != null && !Paths.get(filePart.getSubmittedFileName()).getFileName().toString().isEmpty()) {
                    InputStream image = filePart.getInputStream();
                    imageId = imageService.createImage(image);

                    //delete old image
                    if (existsPeriodical.getImageId() != null) {
                        imageService.deleteImageById(existsPeriodical.getImageId());
                    }*/
                //}

                Periodical periodical = new Periodical();
                periodical.setId(id);
                periodical.setName(request.getParameter("title"));
                periodical.setAbout(request.getParameter("description"));
                periodical.setCostPerMonth(Float.parseFloat(request.getParameter("price")));
                periodical.setPeriodicalCategory(category);
                periodical.setPublisher(publisher);
                periodical.setActive(request.getParameter("enabled") != null);

                periodicalService.update(periodical);

                request.setAttribute("updateSuccess", true);

                Page<Periodical> page = periodicalService.getPage(1, 10);
                request.setAttribute("page", page);


                resultPage = periodicalsPage;
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse {}, {}, {} to long",
                        request.getParameter("id"), request.getParameter("category"), request.getParameter("publisher"));
            }

        }

        return resultPage;
    }
}