package com.reize.StudyTrack.dto.login;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PasswordResetDTO(
    @NotBlank
    String email,
    
    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    String newPassword, 
    
    @NotBlank
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    String confirmPassword
){}
