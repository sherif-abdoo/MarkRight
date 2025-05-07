package com.MarkRight.Repository;

import com.MarkRight.Models.Task;
import com.MarkRight.Models.TaskAssignment;
import com.MarkRight.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TaskAssignmentRepo extends JpaRepository<TaskAssignment, Integer> {
    Optional<TaskAssignment> findByTaskId(Integer taskId);
    Optional<TaskAssignment> findByTaskIdAndAssignedToUsername(Integer taskId, String assignedToUsername);

    @Query("SELECT t.assignedTo FROM TaskAssignment t WHERE t.task.id = :taskId")
    Optional<User> findAssignedToByTaskId(@Param("taskId") Integer taskId);
    List<TaskAssignment> findByAssignedToUsername(String assignedToUsername);
    List<TaskAssignment> findByAssignedByUsername(String assignedByUsername);
}
