/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam;

import com.epam.dao.*;
import com.epam.dao.impl.*;
import com.epam.exception.DaoException;
import com.epam.model.periodical.Periodical;
import com.epam.model.periodical.PeriodicalCategory;
import com.epam.model.periodical.Publisher;
import com.epam.model.subscription.Subscription;
import com.epam.model.subscription.SubscriptionType;
import com.epam.model.user.User;
import com.epam.model.user.UserType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TestData {

    //This file contains all Test Data for DAO Test.

    public static final SubscriptionType SUBSCRIPTION_TYPE;
    public static final SubscriptionType SUBSCRIPTION_TYPE1;
    public static final SubscriptionType SUBSCRIPTION_TYPE2;
    public static final SubscriptionType SUBSCRIPTION_TYPE3;

    public static final User USER;
    public static final User USER1;
    public static final User USER2;
    public static final User USER3;

    public static final PeriodicalCategory PERIODICAL_CATEGORY;
    public static final PeriodicalCategory PERIODICAL_CATEGORY1;
    public static final PeriodicalCategory PERIODICAL_CATEGORY2;
    public static final PeriodicalCategory PERIODICAL_CATEGORY3;

    public static final Publisher PUBLISHER;
    public static final Publisher PUBLISHER_1;
    public static final Publisher PUBLISHER_2;
    public static final Publisher PUBLISHER_3;

    public static final Periodical PERIODICAL;
    public static final Periodical PERIODICAL1;
    public static final Periodical PERIODICAL2;
    public static final Periodical PERIODICAL3;

    public static final Subscription SUBSCRIPTION;
    public static final Subscription SUBSCRIPTION1;
    public static final Subscription SUBSCRIPTION2;
    public static final Subscription SUBSCRIPTION3;

    public static PeriodicalDao PERIODICAL_DAO;
    public static SubscriptionDao SUBSCRIPTION_DAO;
    public static PublisherDao PUBLISHER_DAO;
    public static PeriodicalCategoryDao PERIODICAL_CATEGORY_DAO;
    public static UserDao USER_DAO;
    public static SubscriptionTypeDao SUBSCRIPTION_TYPE_DAO;

    public static List<Subscription> subscriptionListFromDatabase;
    public static List<Periodical> periodicalListFromDatabase;
    public static List<Publisher> publisherList;
    public static List<PeriodicalCategory> periodicalCategoriesFromDatabase;
    public static List<User> userList;
    public static List<SubscriptionType> subscriptionTypesFromDatabase;

    static {

        PERIODICAL_CATEGORY_DAO = PeriodicalCategoryDaoImpl.getInstance();
        PUBLISHER_DAO = PublisherDaoImpl.getInstance();
        SUBSCRIPTION_DAO = SubscriptionDaoImpl.getInstance();
        USER_DAO = UserDaoImpl.getInstance();
        SUBSCRIPTION_TYPE_DAO = SubscriptionTypeDaoImpl.getInstance();
        PERIODICAL_DAO = PeriodicalDaoImpl.getInstance();

        periodicalCategoriesFromDatabase = PERIODICAL_CATEGORY_DAO.selectAll();
        try {
            publisherList = PUBLISHER_DAO.selectAll();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        periodicalListFromDatabase = PERIODICAL_DAO.selectAll();
        userList = USER_DAO.selectAll();
        subscriptionListFromDatabase = SUBSCRIPTION_DAO.selectAll();

        USER = new User((long) 1201, "Serghei", "Ivanov", "ivanov_s@gmail.com", "ivanov", UserType.MANAGER, "Gagarina street", new BigDecimal(200));
        USER1 = new User((long) 1202, "Ivan", "Kabanov", "kabanov_a@gmail.com", "kabanov", UserType.USER, "Lenina street", new BigDecimal(200));
        USER2 = new User((long) 1203, "Петров", "Дмитрий", "petrov_d@gmail.com", "petrov", UserType.ADMIN, "Gagarina street", new BigDecimal(200));
        USER3 = new User((long) 1204, "Test", "Test", "test@gmail.com", "test", UserType.USER, "Gagarina street", new BigDecimal(200));

        SUBSCRIPTION_TYPE = new SubscriptionType((long) 162, "Месячная", 1, 1.0F);
        SUBSCRIPTION_TYPE1 = new SubscriptionType((long) 163, "Квартальная", 3, 3.0F);
        SUBSCRIPTION_TYPE2 = new SubscriptionType((long) 164, "Полугодие", 6, 6.0F);
        SUBSCRIPTION_TYPE3 = new SubscriptionType((long) 165, "Годовая", 12, 12.0F);

        PERIODICAL_CATEGORY = new PeriodicalCategory((long) 548, "Политика");
        PERIODICAL_CATEGORY1 = new PeriodicalCategory((long) 549, "Спорт");
        PERIODICAL_CATEGORY2 = new PeriodicalCategory((long) 550, "Здоровье");
        PERIODICAL_CATEGORY3 = new PeriodicalCategory((long) 551, "Вне политики");

        PUBLISHER = new Publisher((long) 1573, "ООО ИКАР");
        PUBLISHER_1 = new Publisher((long) 1574, "ОДО ЭЛЕГАНТ");
        PUBLISHER_2 = new Publisher((long) 1575, "ОАО НОВАЯ РУСЬ");
        PUBLISHER_3 = new Publisher((long) 1576, "publisher_4");

        PERIODICAL =
                new Periodical((long) 345, "Народная газета", "Иллюстрированный еженедельник о современной жизни. " +
                        "Подробная информация об образовании, здоровье, зарплатах, пенсиях, кредитах, строительстве жилья и т.д. ",
                        PUBLISHER, PERIODICAL_CATEGORY, 26, 1, 7.90F, true, "/image1");
        PERIODICAL1 =
                new Periodical((long) 346, "Сельская газета", "Общеполитическая газета для молодежи. Выход- четверг. События в стране и мире, " +
                        "образование и трудоустройство, здоровье и красота, досуг, спорт, театр, кино, звезды ТВ и шоу-бизнеса. ",
                        PUBLISHER_1, PERIODICAL_CATEGORY1, 26, 1, 4.51F, true, "/image1");
        PERIODICAL2 =
                new Periodical((long) 347, "Журнал планета", "Каждый месяц мы рассказываем об актуальных общественно- " +
                        "политических событиях, знакомим с традициями и культурой стран мира. На страницах журнала вы узнаете  " +
                        "больше о тайнах нашей планеты. С нами вы всегда будете в курсе всего нового и интересного.\n",
                        PUBLISHER_2, PERIODICAL_CATEGORY2, 1, 3, (float) 4.21, true, "/image1");
        PERIODICAL3 =
                new Periodical((long) 347, "Тестовый журнал", "Журнал для теста",
                        PUBLISHER_3, PERIODICAL_CATEGORY3, 1, 1, 16.22F, true, "/image1");

        SUBSCRIPTION = new Subscription((long) 45, USER, PERIODICAL, SUBSCRIPTION_TYPE, LocalDate.of(2020, 01, 01), LocalDate.of(2020, 03, 01), 30F);
        SUBSCRIPTION1 = new Subscription((long) 46, USER1, PERIODICAL1, SUBSCRIPTION_TYPE1, LocalDate.of(2020, 01, 01), LocalDate.of(2020, 03, 01), 30F);
        SUBSCRIPTION2 = new Subscription((long) 47, USER2, PERIODICAL2, SUBSCRIPTION_TYPE2, LocalDate.of(2020, 01, 01), LocalDate.of(2020, 03, 01), 30F);
        SUBSCRIPTION3 = new Subscription((long) 48, USER3, PERIODICAL3, SUBSCRIPTION_TYPE3, LocalDate.of(2020, 01, 01), LocalDate.of(2020, 03, 01), 30F);
    }
}
