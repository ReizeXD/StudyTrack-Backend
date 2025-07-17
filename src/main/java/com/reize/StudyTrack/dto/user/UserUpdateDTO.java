package com.reize.StudyTrack.dto.user;

import com.reize.StudyTrack.enumerator.Level;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDTO {
    private String name;
    private String email;
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
    private String bio;
    private Level level;
    private Integer points;
}