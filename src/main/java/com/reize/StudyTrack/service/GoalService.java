package com.reize.StudyTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reize.StudyTrack.dto.goal.GoalRequestDTO;
import com.reize.StudyTrack.dto.goal.GoalUpdateDTO;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.Subject;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.GoalRepository;
import com.reize.StudyTrack.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GoalService {
    
    
    private final GoalRepository goalRepository;

    
    private final SharedService sharedService;

    
    private final SubjectRepository subjectRepository;

    public Goal saveGoal(GoalRequestDTO goalRequestDTO){
        User user = sharedService.findUser();
        
        Subject subject =subjectRepository.findById(goalRequestDTO.subjectId()).orElseThrow(() -> new EntityNotFoundException("Essa matéria não existe"));
        
        Goal newGoal = new Goal(user,goalRequestDTO.name(),goalRequestDTO.startDate(),goalRequestDTO.endDate(),subject, goalRequestDTO.expectedHoursPerDay(),goalRequestDTO.isPublic());
        
        return goalRepository.save(newGoal);
    }
    
    public Goal updateGoal(Long id, GoalUpdateDTO goalUpdateDTO){
        User user = sharedService.findUser();
        
        return goalRepository.findById(id).map(existingGoal ->{
            if(!user.getId().equals(existingGoal.getUser().getId())) throw new RuntimeException("Você não tem permissão para atualizar esta meta.");
            if(!goalUpdateDTO.name().isBlank()) existingGoal.setName(goalUpdateDTO.name());
            if(goalUpdateDTO.startDate()!= null) existingGoal.setStartDate(goalUpdateDTO.startDate());
            if(goalUpdateDTO.endDate()!= null) existingGoal.setEndDate(goalUpdateDTO.endDate());
            if(goalUpdateDTO.subjectId() != null) existingGoal.setSubject(subjectRepository.findById(goalUpdateDTO.subjectId()).orElseThrow(() -> new EntityNotFoundException("Essa matéria não existe")));
            if(goalUpdateDTO.expectedHoursPerDay() != null) existingGoal.setExpectedHoursPerDay(goalUpdateDTO.expectedHoursPerDay());
            if(goalUpdateDTO.expectedHoursPerDay() != null) existingGoal.setExpectedHoursPerDay(goalUpdateDTO.expectedHoursPerDay());
            if(goalUpdateDTO.isPublic()!=null) existingGoal.setIsPublic(goalUpdateDTO.isPublic()); 
            if(goalUpdateDTO.isActive()!=null) existingGoal.setIsActive(goalUpdateDTO.isActive());
            return goalRepository.save(existingGoal);
        }).orElseThrow(()-> new EntityNotFoundException("Meta não encontrada com o ID: "+ id));
        
    }
    
    public List<Goal> findAll(){
        User user = sharedService.findUser();

        List<Goal> goals = this.goalRepository.findAllByUserId(user.getId());
        return goals;
    }
    
    public Goal findById(Long id){
        User user = sharedService.findUser();
        
        Goal goal = this.goalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Essa meta não existe"));
        
        if(!goal.getUser().getId().equals(user.getId())) throw new RuntimeException("Essa meta não é sua");
        
        return goal;
    }
    
    public List<Goal> findAllActive(){
        User user = sharedService.findUser();
        
        List<Goal> goals = this.goalRepository.findAllByUserIdAndIsActiveTrue(user.getId());
        
        return goals;

    }
}

