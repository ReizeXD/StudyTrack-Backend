package com.reize.StudyTrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.Subject;

public interface SubjectRepository extends JpaRepository  <Subject, Long>{
    
}
