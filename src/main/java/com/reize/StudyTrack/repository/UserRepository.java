package com.reize.StudyTrack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reize.StudyTrack.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> findByEmail(String email);

}
