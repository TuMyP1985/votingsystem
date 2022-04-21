package ru.java.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.service.testData.RestaurantTestData;
import ru.java.votingsystem.service.testData.UserTestData;
import ru.java.votingsystem.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static ru.java.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class VoteServiceTest extends AbstractServiceTest {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator("registered", "user.registered");
    public static final int VOTE_ID = START_SEQ+16;
    public static final Vote vote = new Vote(VOTE_ID,RestaurantTestData.restaurant,UserTestData.user);

    @Autowired
    VoteService service;

    @Test
    public void get() {
        Vote actual = service.get(VOTE_ID, UserTestData.USER_ID);
        VOTE_MATCHER.assertMatch(actual, vote);
    }

    @Test
    public void Delete() {
        service.delete(VOTE_ID);
        if (service.get(VOTE_ID, UserTestData.USER_ID) != null)
             throw new RuntimeException();
    }
}