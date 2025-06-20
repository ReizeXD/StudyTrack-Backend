package com.reize.StudyTrack.dto.goal;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record GoalRequestDTO (
    @NotBlank
    String name,
    
    @NotNull
    LocalDate startDate,
    
    LocalDate endDate,
    
    @NotNull
    Long subjectId,
    
    @NotNull
    Integer expectedHoursPerDay,

    Boolean isPublic
){}
