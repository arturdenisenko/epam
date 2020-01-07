/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.view;

import com.epam.dao.PeriodicalCategoryDao;
import com.epam.dao.PeriodicalDao;
import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.PeriodicalService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import com.epam.service.impl.PeriodicalServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StartPageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(StartPageServlet.class);
    private final static String index = "/WEB-INF/jsp/index.jsp";
    private static PeriodicalService periodicalService;
    private static PeriodicalDao periodicalDao;
    private static List<Periodical> periodicals;
    private static PeriodicalCategoryService periodicalCategoryService;
    private static PeriodicalCategoryDao periodicalCategoryDao;
    private static List<PeriodicalCategory> periodicalCategories;

    @Override
    public void init() throws ServletException {
        LOGGER.info("Periodical Servlet Initialize");
        periodicalDao = PeriodicalDaoImpl.getInstance();
        periodicalService = new PeriodicalServiceImpl(periodicalDao);
        periodicals = periodicalService.getAll();
        periodicalCategoryDao = PeriodicalCategoryDaoImpl.getInstance();
        periodicalCategoryService = new PeriodicalCategoryServiceImpl(periodicalCategoryDao);
        periodicalCategories = periodicalCategoryService.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("periodicals", periodicals);
        req.setAttribute("periodicalCategories", periodicalCategories);
        req.getRequestDispatcher(index).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
