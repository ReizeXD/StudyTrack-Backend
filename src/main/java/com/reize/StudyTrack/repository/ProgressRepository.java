package com.reize.StudyTrack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.Progress;


public interface ProgressRepository extends JpaRepository<Progress,Long>{
    List<Progress> findAllByGoalId(Long goalId);

}
