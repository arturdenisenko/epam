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

package com.epam.command.admin.periodicals;

import com.epam.command.ServletCommand;
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
import com.epam.util.ImageWorker;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class AddPeriodicalAdminCommand implements ServletCommand {
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

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        addPeriodicalPage = properties.getProperty("adminAddPeriodicalPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("ADD PERIODICAL ADMIN COMMAND EXECUTE");
        String resultPage = addPeriodicalPage;
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        fileFactory.setDefaultCharset("UTF-8");
        ServletFileUpload uploader = new ServletFileUpload(fileFactory);
        List<FileItem> fileItemsList = null;
        try {
            fileItemsList = uploader.parseRequest(request);
            LOGGER.info("Request size =  {}", fileItemsList.size());
            LOGGER.info("0 {} {} ", fileItemsList.get(0).getFieldName(), fileItemsList.get(0).getString());
            LOGGER.info("1 {} {} ", fileItemsList.get(1).getFieldName(), fileItemsList.get(1).getString());
            LOGGER.info("2 {} {} ", fileItemsList.get(2).getFieldName(), fileItemsList.get(2).getString());
            LOGGER.info("3 {} {} ", fileItemsList.get(3).getFieldName(), fileItemsList.get(3).getString());
            LOGGER.info("4 {} {}", fileItemsList.get(4).getFieldName(), fileItemsList.get(4).getString());
            LOGGER.info("5 {} {}", fileItemsList.get(5).getFieldName(), fileItemsList.get(5).getString());
            //LOGGER.info("6 {} {}", fileItemsList.get(6).getFieldName(), fileItemsList.get(6).getString());
            //LOGGER.info("7 {} {}", fileItemsList.get(7).getFieldName(), fileItemsList.get(7).getString());
        } catch (FileUploadException e) {
            LOGGER.error(e.getMessage(), e);
        }

        if (fileItemsList.get(0) != null && fileItemsList.get(1) != null &&
                fileItemsList.get(2) != null && fileItemsList.get(3) != null &&
                fileItemsList.get(4) != null) {
            try {

                //request.getPart
                /*во view, в форму, добавить поле с типом file
                в контроллере получить этот файл (он будет доступен как аргумент с типом MultipartFile)
                провалидировать (пустой/не пустой, изображение или нет, может ли пользователь загружать файлы, не слишком ли файл большой и т.д.)
                получить массив байт (это и есть содержимое файла)
                записать эти байты в базу данных/на файловую систему/куда-то еще*/
                // configures upload settings
                FileItem image = null;
                if (fileItemsList.get(6).getString() != null) {
                    image = fileItemsList.get(6);
                    boolean result = ImageWorker.imageSave(image);
                    LOGGER.info("result  = {} ", result);
                }
                PeriodicalCategory category = new PeriodicalCategory();
                category.setId(Long.parseLong(fileItemsList.get(3).getString()));

                Publisher publisher = new Publisher();
                publisher.setId(Long.parseLong(fileItemsList.get(2).getString()));

                Periodical periodical = new Periodical();
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

                periodicalService.createPeriodical(periodical);

                request.setAttribute("publishers", publisherService.getAll());
                request.setAttribute("categories", periodicalCategoryService.getAll());
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return resultPage;
    }
}

