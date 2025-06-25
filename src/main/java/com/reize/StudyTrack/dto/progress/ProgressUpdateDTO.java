package com.reize.StudyTrack.dto.progress;

import java.time.LocalDateTime;

import com.reize.StudyTrack.enumerator.TirednessLevel;



public record ProgressUpdateDTO(
    LocalDateTime date,
    TirednessLevel tirednessLevel, 
    Integer timeStudied
) {}
