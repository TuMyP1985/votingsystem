package ru.java.votingsystem.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Dish m WHERE m.restaurant.id=:restaurantId ORDER BY m.name")
    List<Dish> getAll(@Param("restaurantId") int restaurantId);

}