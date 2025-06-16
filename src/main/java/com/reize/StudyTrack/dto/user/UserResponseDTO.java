package com.reize.StudyTrack.dto.user;

import com.reize.StudyTrack.enumerator.Level;

public record UserResponseDTO (
    Long id,
    String name,
    String email,
    String bio, 
    Level level,
    Integer points
){}
