package com.epam.view;

import com.epam.model.periodical.Periodical;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.periodical.Publisher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PeriodicalsListView extends HttpServlet {
    private final static String periodicalsJsp = "WEB-INF/jsp/periodicals.jsp";
    private List<Periodical> periodicals;

    @Override
    public void init() throws ServletException {
        periodicals = new CopyOnWriteArrayList<>();
        Publisher publisher_1 = new Publisher(1, "ООО Юкар");
        Publisher publisher_2 = new Publisher(2, "ООО Рекстар");
        Publisher publisher_3 = new Publisher(3, "ООО Ютекс");
        PeriodicalCategory periodicalCategory = new PeriodicalCategory(1, "Политика");
        PeriodicalCategory periodicalCategory_2 = new PeriodicalCategory(1, "Юмор");
        PeriodicalCategory periodicalCategory_3 = new PeriodicalCategory(1, "Кулинария");
        Periodical periodical_1 = new Periodical(1, "Витьбичи", "Витебская газета", publisher_1, periodicalCategory, 12, 6, 10.20);
        Periodical periodical_2 = new Periodical(2, "Ералаш", "Юмористический журнал", publisher_2, periodicalCategory_2, 12, 6, 10.20);
        Periodical periodical_3 = new Periodical(3, "Готовим вкусно", "Учимся готовить вкусно", publisher_3, periodicalCategory_3, 12, 6, 10.20);
        periodicals.add(periodical_1);
        periodicals.add(periodical_2);
        periodicals.add(periodical_3);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setCharacterEncoding("UTF-8");
        //resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("text/html; charset=UTF-8");
        req.setAttribute("periodicals", periodicals);
        req.getRequestDispatcher(periodicalsJsp).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //resp.setCharacterEncoding("UTF-8");
        //resp.setContentType("text/html; charset=UTF-8");
       /* if (!requestIsValid(req)) {
            doGet(req, resp);
        }*/
//TODO Проверить что не так с категорией
        final int id = (int) (Math.random() * 12);
        final String name = req.getParameter("name");
        final String about = req.getParameter("about");
        final String publisher = req.getParameter("publisher");
        final String periodicalCategory = req.getParameter("periodicalCategory");
        final int periodicityInSixMonth = Integer.parseInt(req.getParameter("periodicityInSixMonth"));
        final int minSubscriptionPeriod = Integer.parseInt(req.getParameter("minSubscriptionPeriod"));
        final double costPerMonth = Double.parseDouble(req.getParameter("costPerMonth"));
        Publisher publisher_1 = new Publisher(56, publisher);
        PeriodicalCategory periodicalCategory_1 = new PeriodicalCategory(66, periodicalCategory);

        final Periodical periodical = new Periodical(id, name, about, publisher_1, periodicalCategory_1, periodicityInSixMonth, minSubscriptionPeriod, costPerMonth);


        periodicals.add(periodical);

        doGet(req, resp);

    }

    private boolean requestIsValid(final HttpServletRequest req) {

        return true;
    }
}
