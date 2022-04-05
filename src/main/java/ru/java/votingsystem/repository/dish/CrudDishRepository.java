package ru.java.votingsystem.repository.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Dish m WHERE m.restaurant.id=:restaurantId and m.registered >= :startDate And m.registered < :endDate ORDER BY m.name")
    List<Dish> getAllToday(@Param("restaurantId") int restaurantId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}