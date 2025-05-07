package com.MarkRight.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskDto {
    private String creatorUsername;
    private String assigneeUsername;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean completed = false;
}
