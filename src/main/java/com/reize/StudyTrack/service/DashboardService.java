package com.reize.StudyTrack.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.reize.StudyTrack.dto.dashboard.DashboardOverviewDTO;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.GoalRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DashboardService {
    
    private final GoalRepository goalRepository;

    
    private final SharedService sharedService;
   
    public DashboardOverviewDTO getOverview(){
        User user = sharedService.findUser();
        int totalGoals = goalRepository.countByUser(user);
        int activeGoals = goalRepository.countByUserAndIsActiveTrue(user);
        int totalTime = getTotalStudyTimeByUser(user);

        return new DashboardOverviewDTO(totalGoals, totalTime, activeGoals);

    }
    
    public Integer getTotalStudyTimeByUser(User user){
        List<Goal> goals = goalRepository.findAllByUserId(user.getId());
        int total = 0;
        
        for(Goal goal : goals){
            if(goal.getTargetTimeInMinutes() != null){
                total+=goal.getTargetTimeInMinutes();
            }
        }

        return total;
    }

}
