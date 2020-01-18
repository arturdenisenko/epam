/*
 * @Denisenko Artur
 */

/*
 * @Denisenko Artur
 */

package com.epam.model.periodical;

import java.io.Serializable;
import java.util.Objects;

public class Periodical implements Serializable {

    private Long id;
    private String name;//имя
    private String about;//описание
    private Publisher publisher;//издатель
    private PeriodicalCategory periodicalCategory;//категория издания
    private int periodicityInSixMonth;//периодчность в полугодие
    private int minSubscriptionPeriod;//минимальный переиод подписки
    private Float costPerMonth;//стоимость за месяц
    private boolean active;//доступно к подписке
    private String imageLink; //ссылка на изображение

    public Periodical() {
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Periodical(Long id, String name, String about, Publisher publisher, PeriodicalCategory periodicalCategory, int periodicityInSixMonth, int minSubscriptionPeriod, Float costPerMonth, boolean active, String imageLink) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.publisher = publisher;
        this.periodicalCategory = periodicalCategory;
        this.periodicityInSixMonth = periodicityInSixMonth;
        this.minSubscriptionPeriod = minSubscriptionPeriod;
        this.costPerMonth = costPerMonth;
        this.active = active;
        this.imageLink = imageLink;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
