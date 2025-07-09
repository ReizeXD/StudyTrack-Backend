package com.reize.StudyTrack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.dto.progress.ProgressRequestDTO;
import com.reize.StudyTrack.dto.progress.ProgressUpdateDTO;
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

        goal.setTargetTimeInMinutes(goal.getTargetTimeInMinutes() + progressRequestDTO.timeStudied());
        goalRepository.save(goal);

        return progressRepository.save(progress);
    }

    public Progress updateProgress(Long id, ProgressUpdateDTO progressUpdateDTO){
        User user = sharedService.findUser();
        
        return progressRepository.findById(id).map(existingProgress ->{
            if(!user.getId().equals(existingProgress.getGoal().getUser().getId())) throw new RuntimeException("Você não tem permissão para atualizar este progresso.");
            if(progressUpdateDTO.date() != null) existingProgress.setDate(progressUpdateDTO.date());
            if(progressUpdateDTO.timeStudied() != null){ 
                Goal goal = existingProgress.getGoal();
                goal.setTargetTimeInMinutes(goal.getTargetTimeInMinutes() - existingProgress.getTimeStudied() + progressUpdateDTO.timeStudied());
                existingProgress.setTimeStudied(progressUpdateDTO.timeStudied());
                goalRepository.save(goal);
            }
            if(progressUpdateDTO.tirednessLevel() != null) existingProgress.setTirednessLevel(progressUpdateDTO.tirednessLevel());
            
            return progressRepository.save(existingProgress);
        }).orElseThrow(() -> new EntityNotFoundException("Meta não encontrada com o ID: "+ id));
    }
    
    
    public List<Progress> findAll(long goalId){
        User user = sharedService.findUser();
        Goal goal = this.goalRepository.findById(goalId).orElseThrow(() -> new EntityNotFoundException("Essa meta não existe"));
        
        if(!user.getId().equals(goal.getUser().getId())) throw new RuntimeException("Você não tem permissão para vizualizar este progresso.");

        return progressRepository.findAllByGoalId(goalId);
        
    }
}
