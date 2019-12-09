package com.epam.model.periodical;

public class Periodical {
    private int id;
    private String name;
    private String about;
    private Publisher publisher;//издатель
    private PeriodicalCategory periodicalCategory;//cat of periodical
    private int periodicityInSixMonth;//периодчность в полугодие
    private int minSubscriptionPeriod;//минимальный переиод подписки
    private double costPerMonth;//стоимость за месяц

    public Periodical(int id, String name, String about, Publisher publisher, PeriodicalCategory periodicalCategory, int periodicityInSixMonth, int minSubscriptionPeriod, double costPerMonth) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.publisher = publisher;
        this.periodicalCategory = periodicalCategory;
        this.periodicityInSixMonth = periodicityInSixMonth;
        this.minSubscriptionPeriod = minSubscriptionPeriod;
        this.costPerMonth = costPerMonth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public PeriodicalCategory getPeriodicalCategory() {
        return periodicalCategory;
    }

    public void setPeriodicalCategory(PeriodicalCategory periodicalCategory) {
        this.periodicalCategory = periodicalCategory;
    }

    public int getPeriodicityInSixMonth() {
        return periodicityInSixMonth;
    }

    public void setPeriodicityInSixMonth(int periodicityInSixMonth) {
        this.periodicityInSixMonth = periodicityInSixMonth;
    }

    public int getMinSubscriptionPeriod() {
        return minSubscriptionPeriod;
    }

    public void setMinSubscriptionPeriod(int minSubscriptionPeriod) {
        this.minSubscriptionPeriod = minSubscriptionPeriod;
    }

    public double getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(double costPerMonth) {
        this.costPerMonth = costPerMonth;
    }
}
