package com.reize.StudyTrack.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.login.LoginUserDTO;
import com.reize.StudyTrack.dto.login.PasswordResetDTO;
import com.reize.StudyTrack.dto.login.RecoveryJwtTokenDto;
import com.reize.StudyTrack.dto.mapper.Mapper;
import com.reize.StudyTrack.dto.user.UserRequestDTO;
import com.reize.StudyTrack.dto.user.UserResponseDTO;
import com.reize.StudyTrack.dto.user.UserUpdateDTO;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@Valid @RequestBody UserRequestDTO userRequestDTO){
        try{
            User newUser = this.userService.save(userRequestDTO);
            UserResponseDTO userResponseDTO =Mapper.toUserDto(newUser);
            return ResponseEntity.status(201).body(userResponseDTO);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO userUpdateDTO){
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


    @PostMapping("/recovery")
    public ResponseEntity<String> sendEmail(@Valid @RequestBody String email){
        try {
            userService.sendEmail(email);
            return ResponseEntity.ok("Email enviado com ucesso, verifique sua caixa de entrada!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/recovery")
    public ResponseEntity<String> verifyToken(@RequestParam("token") String token){
        try {
            String email = userService.verifyToken(token);
            return ResponseEntity.ok(email);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/recovery/reset")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetDTO dto) {
        try {
            userService.resetPassword(dto.email(), dto.newPassword(), dto.confirmPassword());
            return ResponseEntity.ok("Senha redefinida com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
