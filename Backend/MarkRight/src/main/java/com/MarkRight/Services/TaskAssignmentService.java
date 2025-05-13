package com.MarkRight.Services;


import com.MarkRight.Models.TaskAssignment;
import com.MarkRight.Repository.TaskAssignmentRepo;
import com.MarkRight.Utils.JSendResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.MarkRight.Models.TaskAssignmentStatus.*;

@Service
@AllArgsConstructor
public class TaskAssignmentService {

    private final TaskAssignmentRepo taskAssignmentRepo;

    public JSendResponse acceptAssignment(int taskId) {
        Map<String, Object> response = new HashMap<>();
        TaskAssignment taskAssignment = taskAssignmentRepo.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task Assignment Not Found"));
        taskAssignment.setStatus(ACCEPTED);
        taskAssignmentRepo.save(taskAssignment);
        response.put("message", "Task Assignment Accepted Successfully");
        return JSendResponse.success(response);
    }
    public JSendResponse rejectAssignment(int taskId) {
        Map<String, Object> response = new HashMap<>();
        TaskAssignment taskAssignment = taskAssignmentRepo.findByTaskId(taskId)
                .orElseThrow(() -> new RuntimeException("Task Assignment Not Found"));
        taskAssignment.setStatus(REJECTED);
        taskAssignmentRepo.save(taskAssignment);
        response.put("message", "Task Assignment Rejected Successfully");
        return JSendResponse.success(response);
    }
    public Map<String, Integer> getAssignerTaskCounts(String username) {
        List<Object[]> results = taskAssignmentRepo.
                countAcceptedTasksAssignedToUserGroupedByAssigner(username);
        Map<String, Integer> assignerMap = new HashMap<>();

        for (Object[] row : results) {
            String assignerUsername = (String) row[0];
            if(assignerUsername.equals(username)){
                assignerUsername = "ME";
            }
            Long count = (Long) row[1];
            assignerMap.put(assignerUsername, count.intValue());
        }

        return assignerMap;
    }

}
