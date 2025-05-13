package com.MarkRight.Mappers;

import com.MarkRight.Dto.AssignerDto;
import com.MarkRight.Models.User;

public class AssignerMapper {
    public static AssignerDto toDto(User user){
        AssignerDto assignerDto = new AssignerDto();
        assignerDto.setId(user.getId());
        assignerDto.setUsername(user.getUsername());
        return assignerDto;
    }
}
