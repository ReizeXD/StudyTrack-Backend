package com.reize.StudyTrack.dto.user;

import com.reize.StudyTrack.enumerator.Level;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String email;
    private String password;
    private String bio;
    private Level level;
    private Integer points;
}