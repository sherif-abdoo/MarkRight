package com.MarkRight.Controller;

import com.MarkRight.Dto.ProfileDto;
import com.MarkRight.Mappers.ProfileMapper;
import com.MarkRight.Models.User;
import com.MarkRight.Services.ProfileService;
import com.MarkRight.Services.UserService;
import com.MarkRight.Utils.JSendResponse;
import com.MarkRight.Utils.JSendResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/profile")
public class ProfileController {
    private final UserService userService;
    private final ProfileService profileService;
    @GetMapping("/{username}")
    public ResponseEntity<JSendResponse> getProfile(@PathVariable String username) {
        User user = (User) userService.loadUserByUsername(username);
        return JSendResponseBuilder.build(profileService.getProfile(username));
    }
}














