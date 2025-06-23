package com.reize.StudyTrack.dto.progress;

import java.time.LocalDateTime;

import com.reize.StudyTrack.enumerator.TirednessLevel;

import jakarta.validation.constraints.NotNull;

public record ProgressRequestDTO(

    @NotNull
    Long goalId,

    String goalName,

    @NotNull
    LocalDateTime date,
    @NotNull
    TirednessLevel tirednessLevel, 
    @NotNull
    Integer timeStudied
) {}
