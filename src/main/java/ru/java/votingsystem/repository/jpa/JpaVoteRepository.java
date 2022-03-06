package ru.java.votingsystem.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.repository.DishRepository;
import ru.java.votingsystem.repository.VoteRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaVoteRepository implements VoteRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Vote save(Vote vote) {
        if (vote.isNew()) {
            em.persist(vote);
            return vote;
        } else {
            return em.merge(vote);
        }
    }

    @Override
    public Vote get(int id) {
        return em.find(Vote.class, id);
    }

    @Override
    @Transactional
    public boolean delete(int id) {

        return em.createNamedQuery(Vote.DELETE)
                .setParameter("id", id)
                .executeUpdate() != 0;
    }


    @Override
    public List<Vote> getAll() {
        return em.createNamedQuery(Vote.ALL_SORTED, Vote.class).getResultList();
    }
}
