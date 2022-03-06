package ru.java.votingsystem.repository;

import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.User;

import java.util.List;

public interface RestaurantRepository {
    // null if not found, when updated
    Restaurant save(Restaurant restaurant);

    // false if not found
    boolean delete(int id);

    // null if not found
    Restaurant get(int id);

    List<Restaurant> getAll();
}