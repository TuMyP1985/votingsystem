package ru.java.votingsystem.util;

import ru.java.votingsystem.model.Role;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.to.UserTo;

public class UserUtil {

    public static final int DEFAULT_CALORIES_PER_DAY = 2000;

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setPassword(userTo.getPassword());
        return user;
    }
}