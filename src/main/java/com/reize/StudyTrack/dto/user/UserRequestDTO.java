package com.reize.StudyTrack.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
}

