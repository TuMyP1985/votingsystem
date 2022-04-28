package ru.java.votingsystem.util;

import ru.java.votingsystem.model.Role;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.repository.user.DataJpaUserRepository;
import ru.java.votingsystem.service.UserService;
import ru.java.votingsystem.to.UserTo;
import ru.java.votingsystem.web.SecurityUtil;

public class UserUtil {


    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setPassword(userTo.getPassword());
        return user;
    }
}