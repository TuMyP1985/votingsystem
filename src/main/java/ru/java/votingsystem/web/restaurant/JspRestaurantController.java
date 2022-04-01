package ru.java.votingsystem.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/restaurants")
public class JspRestaurantController extends AbstractRestaurantController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", super.get(getId(request)));
        return "restaurantForm";
    }
    @GetMapping("/dishes")
    public String dishes(HttpServletRequest request, Model model) {
        int i = getId(request);
        List<Dish> dishes = super.getDishes(i);
        model.addAttribute("dishes", dishes);
        Restaurant restaurant = null;
        if (dishes.size()>0)
            restaurant = dishes.get(0).getRestaurant();
        else
            restaurant = super.get(i);
        model.addAttribute("restaurant", restaurant);
        return "dishes";
    }

    @PostMapping("/dishesUpdateOrCreate")
    public String updateOrCreateDishes(HttpServletRequest request, Model model) {
        int idRestaurant = Integer.parseInt(request.getParameter("idRestaurant"));
        Restaurant restaurant = super.get(idRestaurant);
        Dish dish = new Dish(null,
                request.getParameter("name"),
                Integer.parseInt(request.getParameter("price")),
                restaurant);
        dish.setRestaurant(restaurant);
        if (request.getParameter("id").isEmpty()) {
            super.createDish(dish);
        } else {
            super.updateDish(dish, getId(request));
        }
//        return "redirect:/restaurants/dishes";

        model.addAttribute("dishes", super.getDishes(idRestaurant));
        model.addAttribute("restaurant", restaurant);
        return "dishes";
    }

    @GetMapping("/dishes/update")
    public String dishesUpdate(HttpServletRequest request, Model model) {
        model.addAttribute("dish", super.getDish(getId(request)));
        return "dishForm";
    }

    @GetMapping("/dishes/create")
    public String dishesCreate(HttpServletRequest request, Model model) {
        Dish dish = new Dish();
        dish.setRestaurant(super.get(getId(request)));
        model.addAttribute("dish", dish);
        return "dishForm";
    }

    @GetMapping("/dishes/delete")
    public String dishesDelete(HttpServletRequest request, Model model) {
        int idRestaurant = super.getDish(getId(request)).getRestaurant().getId();//bad -  2 request in DB
        super.deleteDishes(getId(request));
        //return "redirect:/restaurants/dishes";

        Restaurant restaurant = super.get(idRestaurant);
        model.addAttribute("dishes", super.getDishes(idRestaurant));
        model.addAttribute("restaurant", restaurant);
        return "dishes";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("restaurant", new Restaurant(""));
        return "restaurantForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Restaurant restaurant = new Restaurant(request.getParameter("name"));

        if (request.getParameter("id").isEmpty()) {
            super.create(restaurant);
        } else {
            super.update(restaurant, getId(request));
        }
        return "redirect:/restaurants";
    }

       private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
