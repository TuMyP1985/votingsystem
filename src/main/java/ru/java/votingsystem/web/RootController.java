package ru.java.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.service.RestaurantService;
import ru.java.votingsystem.service.UserService;
import ru.java.votingsystem.service.VoteService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static ru.java.votingsystem.util.ValidationUtil.canInputVote;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

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
        int userId = SecurityUtil.authUserId();
        model.addAttribute("canInputVote",canInputVote());
        model.addAttribute("restaurants",
                restaurantService.getAll());
        Vote vote = voteService.getWithUser(userId);
        model.addAttribute("vote",vote);
        return "restaurants";
    }
}
