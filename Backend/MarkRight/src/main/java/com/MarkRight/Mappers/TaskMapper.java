package com.MarkRight.Mappers;

import com.MarkRight.Dto.TaskDto;
import com.MarkRight.Models.Task;
import com.MarkRight.Models.User;
import com.MarkRight.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final UserService userService;

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
}
