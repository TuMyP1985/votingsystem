package ru.java.votingsystem.repository.vote;

import ru.java.votingsystem.model.Vote;

import java.util.List;

public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int userId);

    // false if not found
    boolean delete(int id);

    // null if not found
    Vote get(int id, int userId);

    List<Vote> getAll();

    Vote getWithUser(int userId);
}