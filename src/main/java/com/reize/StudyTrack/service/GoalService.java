package com.reize.StudyTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.auth.UserDetailsImpl;
import com.reize.StudyTrack.dto.goal.GoalRequestDTO;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.Subject;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.GoalRepository;
import com.reize.StudyTrack.repository.SubjectRepository;
import com.reize.StudyTrack.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GoalService {
    
    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubjectRepository subjectRepository;

    public Goal saveGoal(GoalRequestDTO goalRequestDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getId()).get();

        try{
            Subject subject =subjectRepository.findById(goalRequestDTO.subjectId()).orElseThrow(() -> new EntityNotFoundException("Essa matéria não existe"));
            Goal newGoal = new Goal(user,goalRequestDTO.name(),goalRequestDTO.startDate(),goalRequestDTO.endDate(),subject, goalRequestDTO.expectedHoursPerDay(),goalRequestDTO.isPublic());
            return goalRepository.save(newGoal);
        }catch(RuntimeException e){
            throw new RuntimeException("Algo de errado ao tentar criar a meta");
        }
    }
}
