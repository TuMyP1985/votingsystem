package ru.java.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.service.testData.DishTestData;
import ru.java.votingsystem.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static ru.java.votingsystem.service.testData.DishTestData.*;

public class DishServiceTest extends AbstractServiceTest{

    @Autowired
    private DishService service;

    @Test
    public void create() {
        Dish created = service.create(getNew());
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(service.get(newId), newDish);
    }

    @Test
    public void delete() {
        service.delete(DISH_ID);
        assertThrows(NotFoundException.class, () -> service.get(DISH_ID));
    }

    @Test
    public void get() {
        Dish actual = service.get(DISH_ID);
        DISH_MATCHER.assertMatch(actual, DishTestData.dish);
    }

    @Test
    public void update() {
        Dish updated = getUpdated();
        service.update(updated);
        DISH_MATCHER.assertMatch(service.get(DISH_ID), getUpdated());
    }
}