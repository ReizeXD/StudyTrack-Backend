package com.reize.StudyTrack.dto.progress;

import java.time.LocalDate;
import com.reize.StudyTrack.enumerator.TirednessLevel;



public record ProgressUpdateDTO(
    LocalDate date,
    TirednessLevel tirednessLevel, 
    Integer timeStudied
) {}
