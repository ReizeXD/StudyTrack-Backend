package com.reize.StudyTrack.dto.goal;

import java.time.LocalDate;

import com.reize.StudyTrack.dto.subject.SubjectDTO;
import com.reize.StudyTrack.dto.user.UserNameDTO;

public record GoalResponseDTO(
    Long id,

    UserNameDTO user,

    String name,
    
    LocalDate startDate,
    
    LocalDate endDate,
    
    SubjectDTO subject,
    
    Integer expectedHoursPerDay,

    Boolean isPublic,

    Boolean isActive,

    Integer targetTimeInMinutes
) {
    
}
