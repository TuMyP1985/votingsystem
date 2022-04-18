package ru.java.votingsystem.service.testData;

import ru.java.votingsystem.model.Role;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.service.MatcherFactory;
import java.util.Collections;
import java.util.Date;

import static ru.java.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered");

    public static final int USER_ID = START_SEQ;
    public static final int GUEST_ID2 = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final int NOT_FOUND = 10;

    public static final User user = new User(USER_ID, "User1", "password1", Role.USER);
    public static final User user2 = new User(GUEST_ID2, "User2", "password2", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", "passwordA", Role.ADMIN);


    public static User getNewUser() {
        return new User(null, "New", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        User updated = new User(user);
        updated.setName("UpdatedName");
        updated.setPassword("newPass");
        updated.setEnabled(false);
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
