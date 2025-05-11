package com.MarkRight.Repository;

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

    @Query("""
    SELECT t FROM TaskAssignment t
    WHERE 
        (t.assignedTo.username = :username AND t.status = com.MarkRight.Models.TaskAssignmentStatus.ACCEPTED)
    """)
    List<TaskAssignment> findAllAcceptedAssignedTasksToUser(@Param("username") String username);

    @Query("""
    SELECT t FROM TaskAssignment t
    WHERE 
       (t.assignedTo.username = :username AND t.status = com.MarkRight.Models.TaskAssignmentStatus.PENDING)
    """)
    List<TaskAssignment> findAllPendingAssignedTasksToUser(@Param("username") String username);

    @Query("""
    SELECT t FROM TaskAssignment t
    WHERE 
        (t.assignedBy.username = :username AND NOT t.assignedTo.username = :username )
    """)
    List<TaskAssignment> findAllCreatedAssignedTasksToUser(@Param("username") String username);

    List<TaskAssignment> findByAssignedToUsername(String assignedToUsername);
    List<TaskAssignment> findByAssignedByUsername(String assignedByUsername);
}
