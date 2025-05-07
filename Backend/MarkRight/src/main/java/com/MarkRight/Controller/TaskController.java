package com.MarkRight.Controller;

import com.MarkRight.Dto.TaskDto;
import com.MarkRight.Models.Task;
import com.MarkRight.Services.TaskService;
import com.MarkRight.Services.UserService;
import com.MarkRight.Utils.CookiesUtils;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JSendResponseBuilder;
import com.MarkRight.Utils.JwtUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final UserService userService;
    private final JwtUtils jwtUtils;
    @PostMapping("/create")
    public ResponseEntity<JSendResponse> createTask(@RequestBody TaskDto taskDto,
                                                    HttpServletRequest request){

        String accessToken = CookiesUtils.extractFromCookies(request, "access_token");
        if (accessToken == null) {
            return JSendResponseBuilder.build(JSendResponse.fail(Map.of("error", "Access token not found in cookies")));
        }

        try {
            String creatorUsername = jwtUtils.getUsernameFromToken(accessToken);
            taskDto.setCreatorUsername(creatorUsername);
        } catch (Exception e) {
            return JSendResponseBuilder.build(
                    JSendResponse.fail(Map.of("error", e.getMessage()))
            );
        }
        JSendResponse response = taskService.createTask(taskDto);
        return JSendResponseBuilder.build(response);
    }

    @GetMapping("/get_tasks")
    public ResponseEntity<JSendResponse> getAllTasks(HttpServletRequest request){
        String accessToken = CookiesUtils.extractFromCookies(request, "access_token");
        if (accessToken == null) {
            return JSendResponseBuilder.build(JSendResponse.fail(Map.of("error", "Access token not found in cookies")));
        }


        try {
            String creatorUsername = jwtUtils.getUsernameFromToken(accessToken);
            return JSendResponseBuilder.build(taskService.getAllTasksByUsername(creatorUsername));
        } catch (Exception e) {
            return JSendResponseBuilder.build(JSendResponse.fail(Map.of("error", e.getMessage())));
        }
    }
}
