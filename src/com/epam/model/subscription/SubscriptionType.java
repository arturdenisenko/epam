package com.epam.model.subscription;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionType implements Serializable {
    private int id;
    private String name;
    private int durationByMonth;
    private Float priceMultiplier;

    public SubscriptionType(int id, String name, int durationByMonth, Float priceMultiplier) {
        this.id = id;
        this.name = name;
        this.durationByMonth = durationByMonth;
        this.priceMultiplier = priceMultiplier;
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

    public int getDurationByMonth() {
        return durationByMonth;
    }

    public void setDurationByMonth(int durationByMonth) {
        this.durationByMonth = durationByMonth;
    }

    public Float getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(Float priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionType that = (SubscriptionType) o;
        return durationByMonth == that.durationByMonth &&
                Double.compare(that.priceMultiplier, priceMultiplier) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, durationByMonth, priceMultiplier);
    }

    @Override
    public String toString() {
        return "SubscriptionType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", durationByMonth=" + durationByMonth +
                ", priceMultiplier=" + priceMultiplier +
                '}';
    }
}
