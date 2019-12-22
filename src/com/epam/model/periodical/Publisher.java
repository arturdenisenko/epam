package com.epam.model.periodical;

import java.io.Serializable;

public class Publisher implements Serializable {
    private int id;
    private String name;

    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Publisher(String name) {
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
    public String toString() {
        return "Publisher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
