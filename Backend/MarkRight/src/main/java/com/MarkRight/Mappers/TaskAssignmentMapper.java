package com.MarkRight.Mappers;

import com.MarkRight.Dto.TaskAssignmentDto;
import com.MarkRight.Dto.TaskDto;
import com.MarkRight.Models.TaskAssignment;
import org.springframework.stereotype.Component;


@Component
public class TaskAssignmentMapper {

    public static TaskAssignmentDto toDto(TaskAssignment assignment) {
        TaskAssignmentDto dto = new TaskAssignmentDto();
        dto.setCreatorUsername(assignment.getAssignedBy().getUsername());
        dto.setAssigneeUsername(assignment.getAssignedTo().getUsername());
        dto.setDescription(assignment.getTask().getDescription());
        dto.setStartDate(assignment.getTask().getStartsAt());
        dto.setEndDate(assignment.getTask().getEndsAt());
        dto.setCompleted(assignment.getTask().isCompleted());
        return dto;
    }
}
