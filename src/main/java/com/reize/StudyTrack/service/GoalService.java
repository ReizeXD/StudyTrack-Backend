package com.reize.StudyTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.dto.goal.GoalRequestDTO;
import com.reize.StudyTrack.dto.goal.GoalUpdateDTO;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.Subject;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.GoalRepository;
import com.reize.StudyTrack.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GoalService {
    
    @Autowired
    GoalRepository goalRepository;

    @Autowired
    SharedService sharedService;

    @Autowired
    SubjectRepository subjectRepository;

    public Goal saveGoal(GoalRequestDTO goalRequestDTO){
        User user = sharedService.findUser();
       
        Subject subject =subjectRepository.findById(goalRequestDTO.subjectId()).orElseThrow(() -> new EntityNotFoundException("Essa matéria não existe"));

        Goal newGoal = new Goal(user,goalRequestDTO.name(),goalRequestDTO.startDate(),goalRequestDTO.endDate(),subject, goalRequestDTO.expectedHoursPerDay(),goalRequestDTO.isPublic());

        return goalRepository.save(newGoal);
    }
    public Goal updateGoal(Long id, GoalUpdateDTO goalUpdateDTO){
        SecurityContextHolder.getContext().getAuthentication();

        return goalRepository.findById(id).map(existingGoal ->{
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
}

