package com.MarkRight.Mappers;

import com.MarkRight.Dto.ProfileDto;
import com.MarkRight.Models.User;
import com.MarkRight.Repository.TaskAssignmentRepo;
import com.MarkRight.Services.TaskAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@AllArgsConstructor
public class ProfileMapper {
    private final TaskAssignmentRepo taskAssignmentRepo;
    private final TaskAssignmentService taskAssignmentService;
    public ProfileDto toDto(User user){
        String username = user.getUsername();
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(user.getId());
        profileDto.setUsername(user.getUsername());
        profileDto.setTotalTasks(taskAssignmentRepo.countTotalTasksForUser(username));
        profileDto.setDoneTasks(taskAssignmentRepo.countDoneTasksForUser(username));
        profileDto.setActiveTasks(taskAssignmentRepo.countActiveTasksForUser(username));
        profileDto.setUrgentTasks(taskAssignmentRepo.countUrgentTasksForUser(username));
        profileDto.setAssigners(taskAssignmentService.getAssignerTaskCounts(username));
        return profileDto;
    }
}
