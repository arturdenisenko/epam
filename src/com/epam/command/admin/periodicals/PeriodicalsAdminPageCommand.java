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

import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.util.GetMappingPropertiesUtil;
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PeriodicalsAdminPageCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicalsAdminPageCommand.class);

    private static PeriodicalService periodicalService;

    private static String page;

    public PeriodicalsAdminPageCommand() {
        LOGGER.info("PERIODICALS ADMIN PAGE COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetMappingPropertiesUtil properties = GetMappingPropertiesUtil.getInstance();
        page = properties.getProperty("adminPeriodicalsPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("PERIODICALS ADMIN PAGE COMMAND EXECUTE");

        try {
            Integer pageNum = Integer.parseInt(request.getParameter("p"));
            Integer size = Integer.parseInt(request.getParameter("s"));

            Page<Periodical> page = periodicalService.getPage(pageNum, size);

            request.setAttribute("page", page);
        } catch (NumberFormatException ex) {
            LOGGER.info("Couldn't parse {}, {} to long", request.getParameter("p"), request.getParameter("s"));
        }

        return page;
    }
}
