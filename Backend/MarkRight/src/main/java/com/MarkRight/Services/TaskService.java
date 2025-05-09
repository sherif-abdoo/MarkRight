package com.MarkRight.Services;

import com.MarkRight.Dto.TaskAssignmentDto;
import com.MarkRight.Dto.TaskDto;
import com.MarkRight.Mappers.TaskAssignmentMapper;
import com.MarkRight.Mappers.TaskMapper;
import com.MarkRight.Models.Task;
import com.MarkRight.Models.TaskAssignment;
import com.MarkRight.Models.TaskAssignmentStatus;
import com.MarkRight.Models.User;
import com.MarkRight.Repository.TaskAssignmentRepo;
import com.MarkRight.Repository.TaskRepo;
import com.MarkRight.Utils.JSendResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepo taskRepo;
    private final UserService userService;
    private final TaskMapper taskMapper;
    private final TaskAssignmentRepo taskAssignmentRepo;
    private final TaskAssignmentMapper taskAssignmentMapper;

    public JSendResponse createTask(TaskDto taskDto) {
        Task task = taskMapper.toTask(taskDto);
        taskRepo.save(task);
        assignTask(task, taskDto.getAssigneeUsername());
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task created successfully");
        return JSendResponse.success(response);
    }

    public JSendResponse updateTask(Task task , boolean completed) {
        Map<String, Object> response = new HashMap<>();
        if(task.isCompleted() == completed) {
            response.put("message", "Task has same status already");
            return JSendResponse.success(response);
        }
        task.setCompleted(completed);
        taskRepo.save(task);
        response.put("message", "Task updated successfully");
        return JSendResponse.success(response);
    }

    public JSendResponse deleteTask(Task task) {
        try{
            taskRepo.delete(task);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Task deleted successfully");
            return JSendResponse.success(response);
        }catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Error deleting task");
            return JSendResponse.success(response);
        }
    }

    public JSendResponse getAllTasksByUsername(String username) {
        Map<String, Object> response = new HashMap<>();
        List<TaskAssignmentDto> tasks = taskAssignmentRepo.findAllAssignedTasksToUser(username)
                .stream().map(TaskAssignmentMapper::toDto).collect(Collectors.toList());
        response.put("tasks", tasks);
        return JSendResponse.success(response);
    }


    public JSendResponse getTasksByDate(int userId , LocalDate searchDate) {
        Map<String, Object> response = new HashMap<>();
        List<Task> tasks = taskRepo.findTasksByDateAndUser(userId , searchDate);
        response.put("tasks", tasks);
        return JSendResponse.success(response);
    }

    public JSendResponse assignTask(Task task , String assigneeUsername) {
        User assineeUser = new User();
        User assigner = task.getTaskCreator();
        Map<String, Object> response = new HashMap<>();
        TaskAssignment taskAssignment = new TaskAssignment();

        //if user assigning task to himself
        if(task.getTaskCreator().getUsername().equals(assigneeUsername)) {
            assineeUser.setId(assigner.getId());
            assineeUser.setUsername("ME");
            taskAssignment.setStatus(TaskAssignmentStatus.ACCEPTED);
        }else{
            assineeUser = (User) userService.loadUserByUsername(assigneeUsername);
        }
        taskAssignment.setTask(task);
        taskAssignment.setAssignedBy(task.getTaskCreator());
        taskAssignment.setAssignedTo(assineeUser);
        taskAssignmentRepo.save(taskAssignment);
        response.put("message", "Task assigned successfully");
        response.put("assigneeUsername", assigneeUsername);
        return JSendResponse.success(response);
    }
}
