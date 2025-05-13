package com.MarkRight.Dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileDto {
    private int id;
    private String username;
    private String profilePic;
    private int totalTasks;
    private int doneTasks;
    private int activeTasks;
    private int urgentTasks;
    private List<AssignerDto> assigners;
}
