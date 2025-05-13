package com.MarkRight.Services;

import com.MarkRight.Dto.ProfileDto;
import com.MarkRight.Mappers.ProfileMapper;
import com.MarkRight.Models.User;
import com.MarkRight.Repository.TaskAssignmentRepo;
import com.MarkRight.Repository.UserRepo;
import com.MarkRight.Utils.JSendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepo userRepo;
    private final TaskAssignmentRepo taskAssignmentRepo;
    private final UserService userService;
    private final ProfileMapper profileMapper;
    public JSendResponse getProfile(String username) {
        User user = (User) userService.loadUserByUsername(username);
        ProfileDto dto = profileMapper.toDto(user);
        return JSendResponse.success(Map.of("profile", dto));
    }
}
