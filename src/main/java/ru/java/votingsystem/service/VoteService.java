package ru.java.votingsystem.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.java.votingsystem.model.Dish;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.Vote;
import ru.java.votingsystem.repository.dish.DishRepository;
import ru.java.votingsystem.repository.vote.VoteRepository;

import java.util.List;

import static ru.java.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    public boolean delete(int id){
        return repository.delete(id);
    }

    public Vote get(int id, int userId){
        return repository.get(id, userId);
    }

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public void selectRestaurant(Vote vote, int idVoteOld, int userId) {
        if (idVoteOld!=0)
            checkNotFoundWithId(repository.delete(idVoteOld), idVoteOld);
        repository.save(vote, userId);
    }

    public Vote getWithUser(int userId){
        return repository.getWithUser(userId);
    }



}