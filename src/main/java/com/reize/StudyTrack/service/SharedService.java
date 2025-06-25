package com.reize.StudyTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.auth.UserDetailsImpl;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.UserRepository;

@Service
public class SharedService {
    
    @Autowired
    UserRepository userRepository;
    
    public User findUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getId()).get();
        return user;
    }
}
