package ru.java.votingsystem.web.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/voteSelect")
    public String voteSelect(HttpServletRequest request) {
        //Vote can be empty
        int idVote = 0;
        if (!request.getParameter("idVote").isEmpty())
            idVote = getAnyId(request, "idVote");

        super.voteSelect(getAnyId(request, "idRestaurant"),
                idVote);
        return "redirect:/restaurants";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("restaurant", super.get(getId(request)));
        return "restaurantForm";
    }
    @GetMapping("/dishes")
    public String dishes(HttpServletRequest request, Model model) {
        int idRestaurant = getId(request);
        setModelDishesRestaurant(model, null, idRestaurant);
        return "dishes";
    }


    public void setModelDishesRestaurant(Model model, Restaurant restaurant, int idRestaurant){
        List<Dish> dishes = super.getAllToday(idRestaurant);
        model.addAttribute("dishes", dishes);
        if (restaurant == null)
            if (dishes.size() > 0)
                restaurant = dishes.get(0).getRestaurant();
            else
                restaurant = super.get(idRestaurant);
        model.addAttribute("restaurant", restaurant);
    }

    @PostMapping("/dishesUpdateOrCreate")
    public String updateOrCreateDishes(HttpServletRequest request, Model model) {
        int idRestaurant = getAnyId(request, "idRestaurant");
        Restaurant restaurant = super.get(idRestaurant);
        Dish dish = new Dish(null,
                request.getParameter("name"),
                getAnyId(request, "price"),
                restaurant);
        dish.setRestaurant(restaurant);
        if (request.getParameter("id").isEmpty()) {
            super.createDish(dish);
        } else {
            super.updateDish(dish, getId(request));
        }
        setModelDishesRestaurant(model, restaurant, idRestaurant);
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
        int idDish = getId(request);
        int idRestaurant = super.getDish(idDish).getRestaurant().getId();//bad -  2 request in DB
        super.deleteDishes(idDish);

        setModelDishesRestaurant(model, null, idRestaurant);

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
        return getAnyId(request,"id");
    }
       private int getAnyId(HttpServletRequest request, String nameId) {
        String paramId = Objects.requireNonNull(request.getParameter(nameId));
        return Integer.parseInt(paramId);
    }
}
