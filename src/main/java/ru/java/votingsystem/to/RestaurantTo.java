package ru.java.votingsystem.to;

import ru.java.votingsystem.model.AbstractNamedEntity;
import ru.java.votingsystem.model.Restaurant;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

public class RestaurantTo{

    private final Integer id;
    private final String name;

    public RestaurantTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
