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
import com.epam.util.ImageUtil;
import com.epam.util.Page;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        fileFactory.setDefaultCharset("UTF-8");
        ServletFileUpload uploader = new ServletFileUpload(fileFactory);
        ServletContext ctx = request.getServletContext();
        String filePath = ctx.getRealPath("./");
        List<FileItem> fileItemsList = null;
        try {
            fileItemsList = uploader.parseRequest(request);
        } catch (FileUploadException e) {
            LOGGER.error(e.getMessage(), e);
        }
        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (fileItemsList.get(0) != null && fileItemsList.get(1) != null &&
                fileItemsList.get(2) != null && fileItemsList.get(3) != null &&
                fileItemsList.get(4) != null && fileItemsList.get(5) != null) {
            try {
                String id = null;
                id = request.getParameter("id");
                Long idL = Long.parseLong(id);
                Periodical existsPeriodical = periodicalService.getPeriodicalById(idL);

                //Get publisher
                Publisher publisher = new Publisher();
                publisher.setId(Long.parseLong(fileItemsList.get(2).getString()));

                //Get category
                PeriodicalCategory category = new PeriodicalCategory();
                category.setId(Long.parseLong(fileItemsList.get(3).getString()));
                FileItem image = null;
                //get image
                try {
                    if (fileItemsList.get(6).getString() != null) {
                        image = fileItemsList.get(6);
                        boolean result = ImageUtil.imageSave(image, filePath);
                        //TODO DELETE IMAGE IF EXISTS
                        LOGGER.info("result  = {} ", result);
                    }
                } catch (Exception e) {
                    LOGGER.warn(e.getMessage(), e);
                }
                Periodical periodical = new Periodical();
                periodical.setId(idL);
                periodical.setName(fileItemsList.get(0).getString());
                periodical.setAbout(fileItemsList.get(4).getString());
                periodical.setCostPerMonth(Float.parseFloat(fileItemsList.get(1).getString()));
                periodical.setPeriodicalCategory(category);
                periodical.setPublisher(publisher);
                if (image != null) {
                    periodical.setImageLink(image.getName());
                } else {
                    periodical.setImageLink("");
                }
                periodical.setActive(fileItemsList.get(5).getString() != null);

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