package com.reize.StudyTrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    List<Goal> findAllByUserId(Long userId);
    
    List<Goal> findAllByUserIdAndIsActiveTrue(Long userId);
}
