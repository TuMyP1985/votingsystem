package ru.java.votingsystem.repository.dish;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.repository.restaurant.CrudRestaurantRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class DataJpaDishRepository {

    private final CrudDishRepository crudRepository;

    public DataJpaDishRepository(CrudDishRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    public Dish get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public List<Dish> getAllToday(int restaurantId) {
        LocalDate today = LocalDate.now();
        return crudRepository.getAllToday(restaurantId, today.atStartOfDay(), today.plus(1, ChronoUnit.DAYS).atStartOfDay());
    }
}
