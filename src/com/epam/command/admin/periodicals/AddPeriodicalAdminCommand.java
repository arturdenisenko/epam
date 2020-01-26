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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddPeriodicalAdminCommand implements ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddPeriodicalAdminCommand.class);

    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "images";

    // upload settings
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

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
        LOGGER.info("ADD MAGAZINE ADMIN COMMAND EXECUTE");
        String resultPage = addPeriodicalPage;
        System.out.println(request.toString());
        LOGGER.info(request.toString());
        if (request.getParameter("title") != null && request.getParameter("price") != null &&
                request.getParameter("publisher") != null && request.getParameter("category") != null &&
                request.getParameter("description") != null) {
            try {
                /*Part filePart = request.getPart("image");
                InputStream fileContent = filePart.getInputStream();
                byte[] buffer = new byte[fileContent.available()];
                fileContent.read(buffer);
                File targetFile = new File( request.getParameter("title")+ File.separator + request.getPart("fileName").getSubmittedFileName());

                try (OutputStream outStream = new FileOutputStream(targetFile)) {
                    outStream.write(buffer);
                    outStream.flush();
                }*/
                //request.getPart
                /*во view, в форму, добавить поле с типом file
                в контроллере получить этот файл (он будет доступен как аргумент с типом MultipartFile)
                провалидировать (пустой/не пустой, изображение или нет, может ли пользователь загружать файлы, не слишком ли файл большой и т.д.)
                получить массив байт (это и есть содержимое файла)
                записать эти байты в базу данных/на файловую систему/куда-то еще*/
// configures upload settings


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
                periodical.setImageLink(request.getParameter("image"));
                periodical.setActive(request.getParameter("enabled") != null);

                periodicalService.createPeriodical(periodical);

                request.setAttribute("publishers", publisherService.getAll());
                request.setAttribute("categories", periodicalCategoryService.getAll());
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse {}, {}, {} to long",
                        request.getParameter("id"), request.getParameter("category"), request.getParameter("publisher"));
            }
        }
        return resultPage;
    }
}



