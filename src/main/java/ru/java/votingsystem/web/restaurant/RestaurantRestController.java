package ru.java.votingsystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.to.RestaurantTo;
import ru.java.votingsystem.util.Util;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.java.votingsystem.util.ValidationUtil.checkNew;

@Controller
public class RestaurantRestController {
    private static final Logger log = LoggerFactory.getLogger(RestaurantRestController.class);

    private final RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return Util.getTos(service.getAll());
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create {}", restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("update {}", restaurant);
        service.update(restaurant);
    }

}