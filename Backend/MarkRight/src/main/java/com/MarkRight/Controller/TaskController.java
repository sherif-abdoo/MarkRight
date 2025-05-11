package com.MarkRight.Controller;

import com.MarkRight.Dto.TaskDto;
import com.MarkRight.Services.TaskAssignmentService;
import com.MarkRight.Services.TaskService;
import com.MarkRight.Services.UserService;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JSendResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final TaskAssignmentService taskAssignmentService;
    @PostMapping("/create")
    public ResponseEntity<JSendResponse> createTask(@RequestBody TaskDto taskDto,
                                                    Authentication auth) {
        String creatorUsername = auth.getName(); // auto-extracted from JWT by Spring Security
        taskDto.setCreatorUsername(creatorUsername);

        JSendResponse response = taskService.createTask(taskDto);
        return JSendResponseBuilder.build(response);
    }

    @PostMapping("accept/{taskId}")
    public ResponseEntity<JSendResponse> acceptTask(Authentication auth, @PathVariable int taskId) {
        String accepterUsername = auth.getName();
        return JSendResponseBuilder.build(taskAssignmentService.acceptAssignment(taskId));
    }
    @PostMapping("reject/{taskId}")
    public ResponseEntity<JSendResponse> rejectTask(Authentication auth, @PathVariable int taskId) {
        String accepterUsername = auth.getName();
        return JSendResponseBuilder.build(taskAssignmentService.rejectAssignment(taskId));
    }

    @PostMapping("done/{taskId}")
    public ResponseEntity<JSendResponse> doneTask(Authentication auth, @PathVariable int taskId) {
        return JSendResponseBuilder.build(taskService.updateTask(taskId));
    }

    @GetMapping("/all")
    public ResponseEntity<JSendResponse> getAllTasks(Authentication auth) {
        String creatorUsername = auth.getName(); // auto-extracted from JWT
        return JSendResponseBuilder.build(taskService.getAllAcceptedTasksByUsername(creatorUsername));
    }

    @GetMapping("/accepted")
    public ResponseEntity<JSendResponse> getAllAcceptedTasks(Authentication auth) {
        String username = auth.getName(); // extracted from JWT
        return ResponseEntity.ok(taskService.getAllAcceptedTasksByUsername(username));
    }

    @GetMapping("/pending")
    public ResponseEntity<JSendResponse> getAllPendingTasks(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(taskService.getAllPendingTasksByUsername(username));
    }

    @GetMapping("/created")
    public ResponseEntity<JSendResponse> getAllCreatedTasks(Authentication auth) {
        String username = auth.getName();
        return ResponseEntity.ok(taskService.getAllCreatedTasksByUsername(username));
    }


}
