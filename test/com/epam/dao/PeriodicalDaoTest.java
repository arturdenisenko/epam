package com.epam.dao;

import com.epam.dao.impl.PeriodicalDaoImpl;
import com.epam.model.periodical.Periodical;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.periodical.Publisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PeriodicalDaoTest {

    public static final PeriodicalCategory PERIODICAL_CATEGORY = new PeriodicalCategory(548, "Политика");
    public static final PeriodicalCategory PERIODICAL_CATEGORY1 = new PeriodicalCategory(549, "Спорт");
    public static final PeriodicalCategory PERIODICAL_CATEGORY2 = new PeriodicalCategory(550, "Здоровье");
    public static final PeriodicalCategory PERIODICAL_CATEGORY3 = new PeriodicalCategory(551, "Вне политики");
    public static final Publisher PUBLISHER = new Publisher(1573, "ООО ИКАР");
    public static final Publisher PUBLISHER_1 = new Publisher(1574, "ОДО ЭЛЕГАНТ");
    public static final Publisher PUBLISHER_2 = new Publisher(1575, "ОАО НОВАЯ РУСЬ");
    public static final Publisher PUBLISHER_3 = new Publisher(1576, "publisher_4");
    public static final Periodical PERIODICAL =
            new Periodical(1, "Народная газета", "Иллюстрированный еженедельник о современной жизни. " +
                    "Подробная информация об образовании, здоровье, зарплатах, пенсиях, кредитах, строительстве жилья и т.д. ",
                    PUBLISHER, PERIODICAL_CATEGORY, 26, 1, 7.90F);
    public static final Periodical PERIODICAL1 =
            new Periodical(2, "Сельская газета", "Общеполитическая газета для молодежи. Выход- четверг. События в стране и мире, " +
                    "образование и трудоустройство, здоровье и красота, досуг, спорт, театр, кино, звезды ТВ и шоу-бизнеса. ",
                    PUBLISHER_1, PERIODICAL_CATEGORY1, 26, 1, 4.51F);
    public static final Periodical PERIODICAL2 =
            new Periodical(3, "Журнал планета", "Каждый месяц мы рассказываем об актуальных общественно- " +
                    "политических событиях, знакомим с традициями и культурой стран мира. На страницах журнала вы узнаете  " +
                    "больше о тайнах нашей планеты. С нами вы всегда будете в курсе всего нового и интересного.\n",
                    PUBLISHER_2, PERIODICAL_CATEGORY2, 1, 3, (float) 4.21);
    public static final Periodical PERIODICAL3 =
            new Periodical(4, "Тестовый журнал", "Журнал для теста",
                    PUBLISHER_3, PERIODICAL_CATEGORY3, 1, 1, 16.22F);
    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodicalDaoTest.class);
    private static final PeriodicalDao PERIODICAL_DAO = PeriodicalDaoImpl.getInstance();
    private static List<Periodical> periodicalListFromDatabase = PERIODICAL_DAO.selectAll();

    @Before
    public void setUp() {
        LOGGER.info("SETUP PERIODICALS TEST");
        PERIODICAL_DAO.clear();
        PERIODICAL_DAO.insert(PERIODICAL);
        PERIODICAL_DAO.insert(PERIODICAL1);
        PERIODICAL_DAO.insert(PERIODICAL2);
        periodicalListFromDatabase = PERIODICAL_DAO.selectAll();
    }

    @Test
    public void insert() {
        LOGGER.info("INSERT PERIODICAL TESTING");
        PERIODICAL_DAO.insert(PERIODICAL3);
        Assert.assertEquals(4, PERIODICAL_DAO.selectAll().size());
    }

    @Test
    public void select() {
        LOGGER.info("SELECT PERIODICAL DATABASE USER TESTING");
        Periodical periodicalForSelectTest = PERIODICAL_DAO.select(periodicalListFromDatabase.get(0).getId());
        Assert.assertEquals(periodicalForSelectTest, PERIODICAL);
    }

    @Test
    public void selectAll() {
        LOGGER.info("SELECT ALL FROM TABLE PERIODICALS TESTING");
        List<Periodical> users = PERIODICAL_DAO.selectAll();
        Assert.assertEquals(users.get(0), PERIODICAL);
        Assert.assertEquals(users.get(1), PERIODICAL1);
        Assert.assertEquals(users.get(2), PERIODICAL2);
    }

    @Test
    public void delete() {
        LOGGER.info("DELETE PERIODICAL TESTING");
        PERIODICAL_DAO.delete(periodicalListFromDatabase.get(0).getId());
        Assert.assertEquals(2, PERIODICAL_DAO.selectAll().size());
    }

    @Test
    public void update() {
        LOGGER.info("UPDATE PERIODICAL TESTING");
        Periodical periodicalForUpdateTest = periodicalListFromDatabase.get(0);
        periodicalForUpdateTest.setName("TESTING UPDATE NAME");
        PERIODICAL_DAO.update(periodicalForUpdateTest);
        Assert.assertEquals(periodicalForUpdateTest, PERIODICAL_DAO.select(periodicalForUpdateTest.getId()));
    }

    @Test
    public void clear() {
        LOGGER.info("CLEAR ALL PERIODICAL TESTING");
        PERIODICAL_DAO.clear();
        Assert.assertEquals(0, PERIODICAL_DAO.selectAll().size());
    }

    @Test
    public void selectByName() {
    }

    @Test
    public void selectByPeriodicalCategory() {
    }
}