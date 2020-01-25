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
import com.epam.model.user.UserType;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalServiceImpl;
import com.epam.util.GetPropertiesUtil;
import com.epam.util.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeletePeriodicalAdminCommand implements com.epam.command.ServletCommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeletePeriodicalAdminCommand.class);

    private static PeriodicalService periodicalService;

    private static String periodicalPage;
    private static String loginPage;

    public DeletePeriodicalAdminCommand() {
        LOGGER.info("DELETE PERIODICAL ADMIN COMMAND INIT");

        periodicalService = new PeriodicalServiceImpl(PeriodicalDaoImpl.getInstance());

        GetPropertiesUtil properties = GetPropertiesUtil.getInstance();
        periodicalPage = properties.getProperty("adminPeriodicalsPage");
        loginPage = properties.getProperty("loginPage");
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("DELETE PERIODICAL ADMIN COMMAND EXECUTE");
        String resultPage = periodicalPage;

        if (request.getSession().getAttribute("authenticated") != null &&
                request.getSession().getAttribute("authenticated").equals(true) &&
                !request.getSession().getAttribute("role").equals(UserType.ADMIN.name())) {
            LOGGER.info("USER ISN'T AUTHORIZED");
            resultPage = loginPage;
        } else if (request.getParameter("id") != null) {
            try {
                Long id = Long.parseLong(request.getParameter("id"));

                request.setAttribute("deletionSuccess", periodicalService.removeById(id));
                Page<Periodical> page = periodicalService.getPage(1, 10);

                request.setAttribute("page", page);
            } catch (NumberFormatException ex) {
                LOGGER.info("Couldn't parse {} to long", request.getParameter("id"));
            }
        }
        return resultPage;
    }
}
