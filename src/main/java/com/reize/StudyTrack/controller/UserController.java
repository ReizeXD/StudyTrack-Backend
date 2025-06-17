package com.reize.StudyTrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.login.LoginUserDTO;
import com.reize.StudyTrack.dto.login.RecoveryJwtTokenDto;
import com.reize.StudyTrack.dto.mapper.Mapper;
import com.reize.StudyTrack.dto.user.UserRequestDTO;
import com.reize.StudyTrack.dto.user.UserResponseDTO;
import com.reize.StudyTrack.dto.user.UserUpdateDTO;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired 
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequestDTO userRequestDTO){
        try{
            User newUser = this.userService.save(userRequestDTO);
            UserResponseDTO userResponseDTO =Mapper.toUserDto(newUser);
            return ResponseEntity.status(201).body(userResponseDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        try {
            User updateUser=this.userService.update(id, userUpdateDTO);
            UserResponseDTO userResponseDTO = Mapper.toUserDto(updateUser);
            return ResponseEntity.status(201).body(userResponseDTO); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginUserDTO loginUserDto){
        try{
            RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
            return ResponseEntity.ok(token);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
