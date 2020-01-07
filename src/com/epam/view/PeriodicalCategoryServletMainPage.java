/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.view;

import com.epam.dao.PeriodicalCategoryDao;
import com.epam.dao.impl.PeriodicalCategoryDaoImpl;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.service.PeriodicalCategoryService;
import com.epam.service.impl.PeriodicalCategoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PeriodicalCategoryServletMainPage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicalCategoryServletMainPage.class);
    private final static String index = "/WEB-INF/jsp/index.jsp";
    private static PeriodicalCategoryDao periodicalCategoryDao;
    private static List<PeriodicalCategory> periodicalCategories;
    private PeriodicalCategoryService periodicalCategoryService;

    @Override
    public void init() throws ServletException {
        LOGGER.info("Periodical Servlet Initialize");
        periodicalCategoryDao = PeriodicalCategoryDaoImpl.getInstance();
        periodicalCategoryService = new PeriodicalCategoryServiceImpl(periodicalCategoryDao);
        periodicalCategories = periodicalCategoryService.getAll();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("periodicalscategories", periodicalCategories);
        req.getRequestDispatcher(index).forward(req, resp);
    }
}
