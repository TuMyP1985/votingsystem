package ru.java.votingsystem.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.java.votingsystem.model.User;
import ru.java.votingsystem.repository.user.DataJpaUserRepository;
import ru.java.votingsystem.to.UserTo;
import ru.java.votingsystem.util.UserUtil;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService {

    private final DataJpaUserRepository repository;

    public UserService(DataJpaUserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(user);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public User get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<User> getAll() {
        return repository.getAll();
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(repository.save(user), user.id());
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        User updatedUser = UserUtil.updateFromTo(user, userTo);
        repository.save(updatedUser);   // !! need only for JDBC implementation
    }
}