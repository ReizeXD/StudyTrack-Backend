package com.reize.StudyTrack.dto.progress;

import java.time.LocalDate;
import com.reize.StudyTrack.enumerator.TirednessLevel;

import jakarta.validation.constraints.NotNull;

public record ProgressRequestDTO(

    @NotNull
    Long goalId,

    String goalName,

    @NotNull
    LocalDate date,
    @NotNull
    TirednessLevel tirednessLevel, 
    @NotNull
    Integer timeStudied
) {}
