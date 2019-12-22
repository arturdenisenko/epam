package com.epam.model.subscription;

import java.util.Objects;

public class SubscriptionType {
    private int id;
    private String name;
    private int durationByMonth;
    private Float priceMultiplier;

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
        return id == that.id &&
                durationByMonth == that.durationByMonth &&
                Objects.equals(name, that.name) &&
                Objects.equals(priceMultiplier, that.priceMultiplier);
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
