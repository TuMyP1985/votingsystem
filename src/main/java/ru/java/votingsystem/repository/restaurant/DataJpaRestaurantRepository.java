package ru.java.votingsystem.repository.restaurant;

import org.springframework.stereotype.Repository;
import ru.java.votingsystem.model.Restaurant;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository {

    private final CrudRestaurantRepository crudRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

     public Restaurant save(Restaurant restaurant) {
        return crudRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }
}
