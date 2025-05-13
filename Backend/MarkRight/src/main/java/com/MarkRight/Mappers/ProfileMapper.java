package com.MarkRight.Mappers;

import com.MarkRight.Dto.ProfileDto;
import com.MarkRight.Models.User;

public class ProfileMapper {
    public static ProfileDto toDto(User user){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(user.getId());
        profileDto.setUsername(user.getUsername());
        return profileDto;
    }
}
