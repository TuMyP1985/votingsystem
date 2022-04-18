package ru.java.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.service.testData.RestaurantTestData;
import ru.java.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.java.votingsystem.service.testData.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest{

    @Autowired
    private RestaurantService service;

    @Test
    public void Create() {
        Restaurant created = service.create(new Restaurant("New"));
        Integer newId = created.getId();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(service.get(newId), newRestaurant);
    }

    @Test
    public void Delete() {
        service.delete(RESTAURANT_ID);
        assertThrows(NotFoundException.class, () -> service.get(RESTAURANT_ID));
    }

    @Test
    public void Get() {
        Restaurant restaurant = service.get(RESTAURANT_ID);
        RESTAURANT_MATCHER.assertMatch(restaurant, RestaurantTestData.restaurant);
    }

    @Test
    public void GetAll() {
        List<Restaurant> all = service.getAll();
        RESTAURANT_MATCHER.assertMatch(all, restaurant, restaurant2, restaurant3);
    }

    @Test
    public void Update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        RESTAURANT_MATCHER.assertMatch(service.get(RESTAURANT_ID), getUpdated());
    }
}