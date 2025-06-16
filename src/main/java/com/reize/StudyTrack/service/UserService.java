package com.reize.StudyTrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.dto.user.UserRequestDTO;
import com.reize.StudyTrack.dto.user.UserUpdateDTO;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User save(UserRequestDTO userRequestDTO){
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), passwordEncoder.encode(userRequestDTO.getPassword())); 

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email ja cadastrado");
        }


    }

    @Transactional
    public User update(Long id, UserUpdateDTO userUpdateDTO){
        return userRepository.findById(id).map(existingUser ->{
            if(userUpdateDTO.getName() != null && !userUpdateDTO.getName().isEmpty()) existingUser.setName(userUpdateDTO.getName());

            if(userUpdateDTO.getEmail()!= null && !userUpdateDTO.getEmail().isEmpty()) existingUser.setEmail(userUpdateDTO.getEmail());
            
            if(userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isEmpty()) existingUser.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
            
            if(userUpdateDTO.getBio()!= null && !userUpdateDTO.getBio().isEmpty()) existingUser.setBio(userUpdateDTO.getBio());

            if(userUpdateDTO.getLevel() != null) existingUser.setLevel(userUpdateDTO.getLevel());

            if(userUpdateDTO.getPoints() != null ) existingUser.setPoints(userUpdateDTO.getPoints() + existingUser.getPoints());

            return userRepository.save(existingUser);
        }).orElseThrow(()-> new EntityNotFoundException("Usuario não encontrado com o ID: "+ id));
    }

}
