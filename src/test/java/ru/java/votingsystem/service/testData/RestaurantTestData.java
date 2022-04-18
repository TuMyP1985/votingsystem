package ru.java.votingsystem.service.testData;

import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.service.MatcherFactory;
import static ru.java.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("");
    public static final int RESTAURANT_ID = START_SEQ+3;

    public static final Restaurant restaurant = new Restaurant(RESTAURANT_ID, "Avrora");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID+1, "Balalayka");
    public static final Restaurant restaurant3 = new Restaurant(RESTAURANT_ID+2, "Rostov");


    public static Restaurant getNew() {
        return new Restaurant(null, "New");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(restaurant);
        updated.setName("UpdatedName");
        return updated;
    }
}
