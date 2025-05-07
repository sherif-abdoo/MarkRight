package com.MarkRight.Mappers;

import com.MarkRight.Dto.TaskDto;
import com.MarkRight.Models.Task;
import com.MarkRight.Models.User;
import com.MarkRight.Repository.TaskAssignmentRepo;
import com.MarkRight.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserService userService;
    private final TaskAssignmentRepo taskAssignmentRepo;

    public Task toTask(TaskDto dto) {
        User creator = (User) userService.loadUserByUsername(dto.getCreatorUsername());
        Task task = new Task();
        task.setTaskCreator(creator);
        task.setDescription(dto.getDescription());
        task.setStartsAt(dto.getStartDate());
        task.setEndsAt(dto.getEndDate());
        task.setCompleted(dto.isCompleted());
        return task;
    }
    public TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        User assignedTo = taskAssignmentRepo.findAssignedToByTaskId(task.getId()).
                orElseThrow(() ->new UsernameNotFoundException("User  not found"));
        dto.setCreatorUsername(task.getTaskCreator().getUsername());
        dto.setAssigneeUsername(assignedTo.getUsername());
        dto.setDescription(task.getDescription());
        dto.setStartDate(task.getStartsAt());
        dto.setEndDate(task.getEndsAt());
        dto.setCompleted(task.isCompleted());
        return dto;
    }
}
