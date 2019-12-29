package com.epam.model.periodical;

import java.io.Serializable;
import java.util.Objects;

public class PeriodicalCategory implements Serializable {
    private int id;
    private String name;

    public PeriodicalCategory(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodicalCategory that = (PeriodicalCategory) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "PeriodicalCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
