package ru.java.votingsystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.service.DishService;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.service.UserService;
import ru.java.votingsystem.service.VoteService;
import ru.java.votingsystem.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.*;

public abstract class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @Autowired
    private DishService dishService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserService userService;

    public Restaurant get(int id) {
        log.info("get restaurant {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete restaurant {}", id);
        service.delete(id);
    }

    public void voteSelect(int idRestaurant, int idVoteOld) {
        if (idVoteOld!=0 && !canInputVote()){
            return;
        }
        int userId = SecurityUtil.authUserId();
        log.info("vote select, restaurant {}, for vote {}", idRestaurant, idVoteOld);
        Vote vote = new Vote(service.get(idRestaurant));
        voteService.selectRestaurant(vote, idVoteOld, userId);
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

    public List<Dish> getAllToday(int idRestaurant) {
        log.info("getDishes for restaurant {}", idRestaurant);
        return dishService.getAllToday(idRestaurant);
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