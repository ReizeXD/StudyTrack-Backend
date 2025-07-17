package com.reize.StudyTrack.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDTO(
    @NotBlank
    @Email
    String to
) {}
