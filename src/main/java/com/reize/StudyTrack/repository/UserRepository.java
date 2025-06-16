package com.reize.StudyTrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
