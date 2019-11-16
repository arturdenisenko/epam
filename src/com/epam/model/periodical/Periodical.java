package com.epam.model.periodical;

public class Periodical {
    private int id;
    private String name;
    private String about;
    private Publisher publisher;//издатель
    private PeriodicalCategory periodicalCategory;//cat of periodical
    private int periodicityInSixMonth;//периодчность в полугодие
    private int minSubscriptionPeriod;//минимальный переиод подписки
    private int costPerMonth;//стоимость за месяц
}
