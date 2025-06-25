package com.reize.StudyTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.dto.progress.ProgressRequestDTO;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.Progress;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.GoalRepository;
import com.reize.StudyTrack.repository.ProgressRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProgressService {

    @Autowired
    ProgressRepository progressRepository;

    @Autowired
    SharedService sharedService;

    @Autowired
    GoalRepository goalRepository;

    public Progress saveProgress(ProgressRequestDTO progressRequestDTO){
        User user = sharedService.findUser();

        Goal goal = goalRepository.findById(progressRequestDTO.goalId()).orElseThrow(() -> new EntityNotFoundException("Essa meta não existe"));

        if(!goal.getUser().getId().equals(user.getId())) throw new RuntimeException("Essa meta não pertence a você");

        Progress progress = new Progress(goal, progressRequestDTO.date(), progressRequestDTO.tirednessLevel(), progressRequestDTO.timeStudied());

        return progressRepository.save(progress);
    }
}
