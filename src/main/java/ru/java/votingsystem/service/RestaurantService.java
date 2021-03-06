package ru.java.votingsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.repository.restaurant.DataJpaRestaurantRepository;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final DataJpaRestaurantRepository repository;

    public RestaurantService(DataJpaRestaurantRepository repository) {
        this.repository = repository;
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant restaurant not be null");
        return repository.save(restaurant);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.id());
    }
}