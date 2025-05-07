package com.MarkRight.Models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class TaskAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Task task;

    @ManyToOne
    @JoinColumn(name = "assigned_by" , nullable = false)
    private User assignedBy;

    @ManyToOne
    @JoinColumn(name = "assigned_to" , nullable = false)
    private User assignedTo;

    @CreationTimestamp
    private LocalDateTime assignedAt;

    @Enumerated(EnumType.STRING)
    private TaskAssignmentStatus status = TaskAssignmentStatus.PENDING;
}
