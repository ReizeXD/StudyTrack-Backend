package com.reize.StudyTrack.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDTO {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;   
    
    @NotBlank
    private String password;
}

