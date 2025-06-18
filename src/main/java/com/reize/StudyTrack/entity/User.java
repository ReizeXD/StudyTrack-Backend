package com.reize.StudyTrack.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.reize.StudyTrack.enumerator.Level;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.EnumType;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotNull(message = "O nome não pode ser nulo")
    private String name;
    
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Email inválido")
    @NotNull(message = "O email não pode ser nulo")
    private String email;

    @Column(name = "password", nullable = false)
    @NotNull(message = "A senha não pode ser nula")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String password;
    
    @Column(name = "bio")
    @Size(max = 200, message = "A bio deve ter no máximo 200 caracteres")
    private String bio;
    
    @Column(name = "points", nullable = false)
    @NotNull(message = "Os pontos não podem ser nulos")
    private Integer points = 0;
    
    @Column(name = "register_date", nullable = false)
    @NotNull(message = "A data não pode ser nula")
    private LocalDateTime registerDate = LocalDateTime.now();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    @NotNull(message = "O nível não pode ser nulo")
    private Level level = Level.BEGINNER;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Goal> goals = new ArrayList<>();

    public User(String name, String email, String password){
        this.name= name;
        this.email = email;
        this.password = password;
    }
}
