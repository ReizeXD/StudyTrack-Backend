package com.reize.StudyTrack.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reize.StudyTrack.auth.JwtTokenService;
import com.reize.StudyTrack.auth.UserDetailsImpl;
import com.reize.StudyTrack.dto.login.LoginUserDTO;
import com.reize.StudyTrack.dto.login.RecoveryJwtTokenDto;
import com.reize.StudyTrack.dto.user.UserRequestDTO;
import com.reize.StudyTrack.dto.user.UserUpdateDTO;
import com.reize.StudyTrack.entity.User;
import com.reize.StudyTrack.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    
    private final PasswordEncoder  passwordEncoder;
    
    private final JwtTokenService jwtTokenService;
    
    private final JavaMailSender sender;
    
    public User save(UserRequestDTO userRequestDTO){
        User user = new User(userRequestDTO.getName(), userRequestDTO.getEmail(), passwordEncoder.encode(userRequestDTO.getPassword())); 

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email ja cadastrado" + e.getMessage());
        }

    }

    @Transactional
    public User update(Long id, UserUpdateDTO userUpdateDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        if(!userDetails.getId().equals(id)){
            throw new RuntimeException("Você não tem permissão para atualizar este usuário.");
        }

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

    public RecoveryJwtTokenDto authenticateUser(LoginUserDTO loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }


    public void sendEmail(String email){
        try {

            userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Voce não é cadastrado"));
            
            SimpleMailMessage message = new SimpleMailMessage();
            
            String token = jwtTokenService.generatePasswordResetToken(email);
            String link = "http://localhost:4200/recovery?token=" + token;
            
            message.setTo(email);
            message.setSubject("Recuperação de Senha");
            message.setText("Clique no link para redefinir sua senha: " + link);
            
            sender.send(message);
        } catch (MailException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public String verifyToken(String token){
        String email = jwtTokenService.getEmailFromResetToken(token);
        
        userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Voce não é cadastrado"));
        return email;
    }
    
    public void resetPassword(String email, String newPassword, String confirmPassword){
        
        if(!newPassword.equals(confirmPassword)) throw new RuntimeException("As senhas nao são iguais");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Voce não é cadastrado"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

}
