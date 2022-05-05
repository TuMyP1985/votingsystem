package ru.java.votingsystem.repository.user;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.java.votingsystem.model.User;

import java.util.List;

@Repository
public class DataJpaUserRepository {

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    public User save(User user) {
        return crudRepository.save(user);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public User getByName(String name) {
        return crudRepository.getByName(name);
    }

    public List<User> getAll() {
        return crudRepository.findAll();
    }

}
