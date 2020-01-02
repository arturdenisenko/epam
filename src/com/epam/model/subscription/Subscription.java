package com.epam.model.subscription;

import com.epam.model.periodical.Periodical;
import com.epam.model.user.User;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Subscription implements Serializable {
    private int id;
    private User user;
    private Periodical periodical;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float cost;
    private SubscriptionType type;

    public Subscription() {
    }

    public Subscription(int id, User user, Periodical periodical, SubscriptionType type, LocalDate startDate, LocalDate endDate, Float cost) {
        this.id = id;
        this.user = user;
        this.periodical = periodical;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public SubscriptionType getType() {
        return type;
    }

    public void setType(SubscriptionType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(periodical, that.periodical) &&
                Objects.equals(type, that.type) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, periodical, type, startDate, endDate, cost);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", user=" + user +
                ", periodical=" + periodical +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                '}';
    }
}