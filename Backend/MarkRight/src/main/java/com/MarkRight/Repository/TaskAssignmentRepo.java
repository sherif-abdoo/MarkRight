package com.MarkRight.Repository;

import com.MarkRight.Models.TaskAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskAssignmentRepo extends JpaRepository<TaskAssignment, Long> {
    Optional<TaskAssignment> findByTaskId(Long taskId);
    Optional<TaskAssignment> findByTaskIdAndAssignedToUsername(Long taskId, String assignedToUsername);
    List<TaskAssignment> findByAssignedToUsername(String assignedToUsername);
    List<TaskAssignment> findByAssignedByUsername(String assignedByUsername);
}
