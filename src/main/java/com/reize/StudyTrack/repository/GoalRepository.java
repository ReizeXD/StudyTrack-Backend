package com.reize.StudyTrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.Goal;

public interface GoalRepository extends JpaRepository<Goal,Long> {
    
}
