package ru.java.votingsystem.util;


import ru.java.votingsystem.model.AbstractBaseEntity;
import ru.java.votingsystem.util.exception.NotFoundException;

import java.time.LocalTime;

public class ValidationUtil {
    private final static int HOUR_BEFORE_INPUT_VOTE=11;
    private ValidationUtil() {
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbstractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
//      conservative when you reply, but accept liberally (http://stackoverflow.com/a/32728226/548473)
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static boolean canInputVote(){
        return (LocalTime.now().getHour()<HOUR_BEFORE_INPUT_VOTE ||
                LocalTime.now().getMinute()==0 && LocalTime.now().getHour()==HOUR_BEFORE_INPUT_VOTE);
    }
}