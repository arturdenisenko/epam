package com.epam.model.subscription;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionType implements Serializable {
    private int id;
    private String name;
    private int durationByMonth;
    private double priceMultiplier;

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

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionType that = (SubscriptionType) o;
        return id == that.id &&
                durationByMonth == that.durationByMonth &&
                Double.compare(that.priceMultiplier, priceMultiplier) == 0 &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationByMonth, priceMultiplier);
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
