package com.reize.StudyTrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.Progress;

public interface ProgressRepository extends JpaRepository<Progress,Long>{
    
}
