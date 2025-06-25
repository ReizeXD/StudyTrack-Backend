package com.reize.StudyTrack.dto.goal;

import java.time.LocalDate;

public record GoalUpdateDTO(
    String name,
    LocalDate startDate,
    LocalDate endDate,
    Long subjectId,
    Integer expectedHoursPerDay,
    Boolean isActive,
    Boolean isPublic
) {}
