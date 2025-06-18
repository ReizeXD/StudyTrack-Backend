package com.reize.StudyTrack.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import com.reize.StudyTrack.enumerator.TirednessLevel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;


@Data
@Entity
public class Progress {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="goal_id")
    @ToString.Exclude
    private Goal goal;

    @Column(name = "date", nullable = false)
    @NotNull(message = "A data não pode ser nula")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "tiredness_level", nullable = false)
    @NotNull(message = "O nivel de cansaço não pode ser nulo")
    private TirednessLevel tirednessLevel;

    @Column(name = "time_studied", nullable = false)
    @NotNull(message = "O tempo estudado não pode ser nulo")
    private Integer timeStudied;

    
}
