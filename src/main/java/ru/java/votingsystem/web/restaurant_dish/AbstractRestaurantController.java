package ru.java.votingsystem.web.restaurant_dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.service.VoteService;
import ru.java.votingsystem.web.SecurityUtil;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public List<Restaurant> getAll() {
        log.info("getAll restaurant");
        return service.getAll();
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {}", restaurant);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }


}