package ru.java.votingsystem.repository.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.java.votingsystem.model.Restaurant;
import ru.java.votingsystem.model.Vote;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT m FROM Vote m WHERE m.user.id = :userId and m.registered >= :startDate And m.registered < :endDate")
    Vote getWithUser(@Param("userId") int userId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}