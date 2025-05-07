package com.MarkRight.Repository;

import com.MarkRight.Models.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
    @Query("SELECT t FROM Task t WHERE t.taskCreator.id = :userId AND :searchDate BETWEEN t.startsAt AND t.endsAt")
    List<Task> findTasksByDateAndUser(@Param("userId") Integer userId, @Param("searchDate") LocalDate searchDate);

    Optional<Task> findTaskByDescription(String description);
}
