package ru.java.votingsystem.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.service.DishService;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.service.UserService;
import ru.java.votingsystem.service.VoteService;
import ru.java.votingsystem.web.SecurityUtil;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.*;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @Autowired
    private DishService dishService;

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public Dish getDish(int idDish) {
        log.info("getDish {}", idDish);
        return dishService.get(idDish);
    }
    public void deleteDishes(int id) {
        log.info("delete Dish {}", id);
        dishService.delete(id);
    }

    public Dish createDish(Dish dish) {
        log.info("create dish {}", dish);
        checkNew(dish);
        return dishService.create(dish);
    }

    public void updateDish(Dish dish, int id) {
        log.info("update dish {}", dish);
        assureIdConsistent(dish, id);
        dishService.update(dish);
    }


}