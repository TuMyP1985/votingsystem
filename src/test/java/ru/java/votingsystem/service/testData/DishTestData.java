package ru.java.votingsystem.service.testData;

import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.service.MatcherFactory;

import static ru.java.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered");
    public static final int DISH_ID = START_SEQ + 6;

    public static final Dish dish = new Dish(DISH_ID, "Овсянка Avrora", 500, RestaurantTestData.restaurant);

    public static Dish getNew() {
        return new Dish(null, "New", 111, RestaurantTestData.restaurant);
    }

    public static Dish getUpdated() {
        Dish updated = new Dish(dish);
        updated.setName("UpdatedName");
        updated.setPrice(111);
        return updated;
    }
}


