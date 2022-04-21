package ru.java.votingsystem.web.restaurant_dish;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.votingsystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = "/votingsys/restaurant", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantUIController extends AbstractRestaurantDishController {

    @Override
    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate(@RequestParam String name,@RequestParam String id) {
            Restaurant restaurant = new Restaurant(null, name);
            if (id.isEmpty()) {
            super.create(restaurant);
            } else {
                super.update(restaurant, Integer.parseInt(id));
            }
            return ResponseEntity.ok().build();

    }
}
