package ru.java.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.java.votingsystem.model.*;
import ru.java.votingsystem.service.DishService;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.service.UserService;
import ru.java.votingsystem.service.VoteService;
import ru.java.votingsystem.util.UserUtil;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

import static ru.java.votingsystem.util.ValidationUtil.canInputVote;

@ApiIgnore
@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    @Autowired
    private DishService dishService;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "login";
        //return "restaurants";
    }

    //    @Secured("ROLE_ADMIN")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    @GetMapping("/restaurants")
    public String getRestaurant(Model model) {
        log.info("restaurants");
        int userId = SecurityUtil.authUserId();
        model.addAttribute("canInputVote", canInputVote());
        model.addAttribute("restaurants", restaurantService.getAll());
        model.addAttribute("itIsAdmin", itIsAdmin());
        Vote vote = voteService.getWithUser(userId);
        vote = vote == null ? new Vote() : vote;
        model.addAttribute("vote", vote);
        return "restaurants";
    }


    @GetMapping("/restaurants/dishes")
    public String dishes(HttpServletRequest request, Model model) {
        int idRestaurant = getId(request);
        setModelDishesRestaurant(model, null, idRestaurant);
        model.addAttribute("itIsAdmin", itIsAdmin());
        return "dishes";
    }

    @GetMapping("/restaurants/voteSelect")
    public String voteSelect(HttpServletRequest request) {

        //Vote can be empty
        int idVoteOld = 0;
        if (!request.getParameter("idVote").isEmpty())
            idVoteOld = getAnyId(request, "idVote");

        if (idVoteOld!=0 && !canInputVote())
            return "redirect:/restaurants";

        int idRestaurant = getAnyId(request, "idRestaurant");
        int userId = SecurityUtil.authUserId();
        log.info("vote select, restaurant {}, for vote {}", idRestaurant, idVoteOld);
        Vote vote = new Vote(restaurantService.get(idRestaurant));
        voteService.selectRestaurant(vote, idVoteOld, userId);

        return "redirect:/restaurants";

    }

    public void setModelDishesRestaurant(Model model, Restaurant restaurant, int idRestaurant) {
        List<Dish> dishes = dishService.getAllToday(idRestaurant);
        model.addAttribute("dishes", dishes);
        if (restaurant == null)
            if (dishes.size() > 0)
                restaurant = dishes.get(0).getRestaurant();
            else
                restaurant = restaurantService.get(idRestaurant);
        model.addAttribute("restaurant", restaurant);
    }


    private int getId(HttpServletRequest request) {
        return getAnyId(request, "id");
    }

    private int getAnyId(HttpServletRequest request, String nameId) {
        String paramId = Objects.requireNonNull(request.getParameter(nameId));
        return Integer.parseInt(paramId);
    }

    private boolean itIsAdmin(){
        return userService.get(SecurityUtil.authUserId()).getRoles().contains(Role.ADMIN);
    }
}
