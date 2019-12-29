package com.epam.model.periodical;

import java.io.Serializable;
import java.util.Objects;

public class Periodical implements Serializable {
    private int id;
    private String name;
    private String about;
    private Publisher publisher;//издатель
    private double price;// Цена
    private PeriodicalCategory periodicalCategory;//cat of periodical
    private int periodicityInSixMonth;//периодчность в полугодие
    private int minSubscriptionPeriod;//минимальный переиод подписки
    private double costPerMonth;//стоимость за месяц

    public Periodical(int id, String name, String about, Publisher publisher, Float price, PeriodicalCategory periodicalCategory, int periodicityInSixMonth, int minSubscriptionPeriod, double costPerMonth) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.publisher = publisher;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodical that = (Periodical) o;
        return id == that.id &&
                periodicityInSixMonth == that.periodicityInSixMonth &&
                minSubscriptionPeriod == that.minSubscriptionPeriod &&
                Double.compare(that.costPerMonth, costPerMonth) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(about, that.about) &&
                Objects.equals(publisher, that.publisher) &&
                Objects.equals(price, that.price) &&
                Objects.equals(periodicalCategory, that.periodicalCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, about, publisher, price, periodicalCategory, periodicityInSixMonth, minSubscriptionPeriod, costPerMonth);
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", publisher=" + publisher +
                ", price=" + price +
                ", periodicalCategory=" + periodicalCategory +
                ", periodicityInSixMonth=" + periodicityInSixMonth +
                ", minSubscriptionPeriod=" + minSubscriptionPeriod +
                ", costPerMonth=" + costPerMonth +
                '}';
    }
}
