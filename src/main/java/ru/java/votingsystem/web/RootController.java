package ru.java.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:restaurants";
    }

    @GetMapping("/restaurants")
    public String getRestaurant(Model model) {
        log.info("restaurants");
        model.addAttribute("restaurants",
                restaurantService.getAll());
        return "restaurants";
    }
}
