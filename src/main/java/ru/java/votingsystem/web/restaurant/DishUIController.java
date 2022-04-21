package ru.java.votingsystem.web.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = "/votingsys/dish", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishUIController extends AbstractRestaurantController{

    @GetMapping
    public List<Dish> getAllToday() {
        return super.getAllToday(1003);
    }

    @Override
    @GetMapping("/{id}")
    public Dish getDish(@PathVariable int id) {
        return super.getDish(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.deleteDishes(id);
    }


    @PostMapping("/{idRest}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate1(@PathVariable int idRest,
                                                 @RequestParam String id,
                                                 @RequestParam String name,
                                                 @RequestParam String price) {
        Dish dish = new Dish(null, name, Integer.parseInt(price),
                super.get(idRest));
//                super.get(Integer.parseInt(idRest)));
        if (id.isEmpty()) {
            super.createDish(dish);
        } else {
            super.updateDish(dish, Integer.parseInt(id));
        }
        return ResponseEntity.ok().build();

    }


    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> createOrUpdate2(@RequestParam String idRest,
                                                 @RequestParam String id,
                                                 @RequestParam String name,
                                                 @RequestParam String price) {
        Dish dish = new Dish(null, name, Integer.parseInt(price),
                super.get(Integer.parseInt(idRest)));
        if (id.isEmpty()) {
            super.createDish(dish);
        } else {
            super.updateDish(dish, Integer.parseInt(id));
        }
        return ResponseEntity.ok().build();

    }
}
