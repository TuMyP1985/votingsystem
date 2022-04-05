package ru.java.votingsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.repository.dish.DishRepository;
import ru.java.votingsystem.repository.restaurant.RestaurantRepository;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepository repository;

    public DishService(DishRepository repository) {
        this.repository = repository;
    }

    public Dish create(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Dish get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Dish> getAllToday(int id) {
        return repository.getAllToday(id);
    }

    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(repository.save(dish), dish.id());
    }
}