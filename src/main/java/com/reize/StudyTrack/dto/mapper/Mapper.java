package com.reize.StudyTrack.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.reize.StudyTrack.dto.goal.GoalResponseDTO;
import com.reize.StudyTrack.dto.progress.ProgressRequestDTO;
import com.reize.StudyTrack.dto.subject.SubjectDTO;
import com.reize.StudyTrack.dto.user.UserNameDTO;
import com.reize.StudyTrack.dto.user.UserResponseDTO;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.Progress;
import com.reize.StudyTrack.entity.Subject;
import com.reize.StudyTrack.entity.User;

public class Mapper {
    public static UserResponseDTO toUserDto(User user){
        return new UserResponseDTO(user.getId(), user.getName(),user.getEmail(),user.getBio(), user.getLevel(), user.getPoints());
    }

    public static GoalResponseDTO toGoalDTO(Goal goal){
        return new GoalResponseDTO(goal.getId(),toUserNameDTO(goal.getUser()), goal.getName(), goal.getStartDate(), goal.getEndDate(), toSubjectDTO(goal.getSubject()), goal.getExpectedHoursPerDay(), goal.getIsPublic(), goal.getIsActive(), goal.getTargetTimeInMinutes());
    }

    public static ProgressRequestDTO toProgressDTO(Progress progress){
        return new ProgressRequestDTO(progress.getGoal().getId(),progress.getGoal().getName(), progress.getDate(),progress.getTirednessLevel(),progress.getTimeStudied());
    }

    public static UserNameDTO toUserNameDTO(User user){
        return new UserNameDTO(user.getId(),user.getName());
    }
    public static SubjectDTO toSubjectDTO(Subject subject){
        return new SubjectDTO(subject.getId(),subject.getName());
    }

    public static List<GoalResponseDTO> toListGoalDTO(List<Goal> goals){
        List<GoalResponseDTO> listGoals = new ArrayList<>();
        for(Goal goal : goals){
            listGoals.add(toGoalDTO(goal));
        }
        return listGoals;
    }

    public static List<SubjectDTO> toListSubjectDTO(List<Subject> subjects){
        List<SubjectDTO> listSubject = new ArrayList<>();
        for(Subject subject : subjects){
            listSubject.add(toSubjectDTO(subject));
        }
        return listSubject;
    }
}
