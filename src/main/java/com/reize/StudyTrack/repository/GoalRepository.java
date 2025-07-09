package com.reize.StudyTrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.entity.User;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    List<Goal> findAllByUserId(Long userId);
    
    List<Goal> findAllByUserIdAndIsActiveTrue(Long userId);

    int countByUser(User user);
    int countByUserAndIsActiveTrue(User user);
}
