package ru.java.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.service.testData.UserTestData;
import ru.java.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.java.votingsystem.service.testData.UserTestData.*;


public class UserServiceTest extends AbstractServiceTest{

    @Autowired
    private UserService service;

    @Test
    public void create() {
        User created = service.create(getNewUser());
        Integer newId = created.getId();
        User newUser = getNewUser();
        newUser.setId(newId);
        USER_MATCHER.assertMatch(created, newUser);
        USER_MATCHER.assertMatch(service.get(newId), newUser);
    }

    @Test
    public void delete() {
        service.delete(USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(USER_ID));
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND));
    }

    @Test
    public void get() {
        User actual = service.get(USER_ID);
        USER_MATCHER.assertMatch(actual, UserTestData.user);

        User user1 = service.get(USER_ID+1);
        USER_MATCHER.assertMatch(user1, UserTestData.user2);

        User user2 = service.get(USER_ID+2);
        USER_MATCHER.assertMatch(user2, admin);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND));
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        USER_MATCHER.assertMatch(service.get(USER_ID), getUpdated());
    }

    @Test
    public void getAll() {
        List<User> all = service.getAll();
        USER_MATCHER.assertMatch(all, user, user2, admin);
    }
}