package com.reize.StudyTrack.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
public class Goal {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    @ToString.Exclude
    private User user;

    @Column(name = "name", nullable = false)
    @NotNull(message = "O nome não pode ser nulo")
    private String name;

    @Column(name = "start_date", nullable = false)
    @NotNull(message = "A data não pode ser nula")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "subject", nullable = false)
    @NotNull(message = "A matéria não pode ser nula")
    private Subject subject;
    
    @Column(name = "expected_hours", nullable=false)
    @NotNull(message = "A hora não pode ser nula")
    private Integer expectedHoursPerDay;

    @Column(name = "target_time")
    private Integer targetTimeInMinutes=0;

    private Boolean isActive = true;

    private Boolean isPublic = true;

    @OneToMany(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progress> progress = new ArrayList<>();

}
