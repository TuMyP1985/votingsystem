package ru.java.votingsystem.util;

import org.springframework.lang.Nullable;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.to.RestaurantTo;

import java.util.List;

public class Util {
    private Util() {
    }

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) < 0);
    }

    public static List<RestaurantTo> getTos(List<Restaurant> restaurants){
        return restaurants.stream()
                .map((Util::createTo))
                        .toList();
    }

    private static RestaurantTo createTo(Restaurant restaurants) {
        return new RestaurantTo(restaurants.getId(), restaurants.getName());
    }
}