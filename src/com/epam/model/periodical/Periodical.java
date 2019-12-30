package com.epam.model.periodical;

import java.io.Serializable;
import java.util.Objects;

public class Periodical implements Serializable {

    private int id;
    private String name;
    private String about;
    private Publisher publisher;//издатель
    private PeriodicalCategory periodicalCategory;//cat of periodical
    private int periodicityInSixMonth;//периодчность в полугодие
    private int minSubscriptionPeriod;//минимальный переиод подписки
    private Float costPerMonth;//стоимость за месяц
    private boolean active;

    public Periodical() {
    }

    public Periodical(int id, String name, String about, Publisher publisher, PeriodicalCategory periodicalCategory, int periodicityInSixMonth, int minSubscriptionPeriod, Float costPerMonth) {
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

    public Float getCostPerMonth() {
        return costPerMonth;
    }

    public void setCostPerMonth(Float costPerMonth) {
        this.costPerMonth = costPerMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodical that = (Periodical) o;
        return periodicityInSixMonth == that.periodicityInSixMonth &&
                minSubscriptionPeriod == that.minSubscriptionPeriod &&
                Objects.equals(name, that.name) &&
                Objects.equals(about, that.about) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(periodicalCategory, that.periodicalCategory) &&
                Objects.equals(costPerMonth, that.costPerMonth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, about, publisher, periodicalCategory, periodicityInSixMonth, minSubscriptionPeriod, costPerMonth);
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", publisher=" + publisher +
                ", periodicalCategory=" + periodicalCategory +
                ", periodicityInSixMonth=" + periodicityInSixMonth +
                ", minSubscriptionPeriod=" + minSubscriptionPeriod +
                ", costPerMonth=" + costPerMonth +
                '}';
    }
}
