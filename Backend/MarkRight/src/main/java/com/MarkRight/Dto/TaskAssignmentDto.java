package com.MarkRight.Dto;


import com.MarkRight.Models.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskAssignmentDto {
    private int taskId;
    private TaskStatus status;
    private String creatorUsername;
    private String assigneeUsername;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed;
}
