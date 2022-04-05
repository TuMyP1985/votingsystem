package ru.java.votingsystem.repository.vote;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.repository.dish.CrudDishRepository;
import ru.java.votingsystem.repository.user.CrudUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Vote save(Vote vote, int userId) {
        if (!vote.isNew() && get(vote.getId(), userId) == null) {
            return null;
        }
        vote.setUser(crudUserRepository.getOne(userId));

        return crudRepository.save(vote);
    }

    @Override
    public Vote get(int id, int userId) {
//        return crudRepository.findById(id).orElse(null);
        return crudRepository.findById(id)
                .filter(vote -> vote.getUser().getId() == userId)
                .orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.findAll();
    }

    @Override
    public Vote getWithUser(int userId) {
        LocalDate today = LocalDate.now();
        return crudRepository.getWithUser(userId, today.atStartOfDay(), today.plus(1, ChronoUnit.DAYS).atStartOfDay());
    }
}