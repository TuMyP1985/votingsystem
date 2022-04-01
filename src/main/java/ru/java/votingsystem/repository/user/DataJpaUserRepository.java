package ru.java.votingsystem.repository.user;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.java.votingsystem.model.User;

import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }

}
