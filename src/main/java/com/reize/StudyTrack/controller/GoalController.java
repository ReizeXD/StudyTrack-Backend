package com.reize.StudyTrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.goal.GoalRequestDTO;
import com.reize.StudyTrack.dto.goal.GoalResponseDTO;
import com.reize.StudyTrack.dto.mapper.Mapper;
import com.reize.StudyTrack.entity.Goal;
import com.reize.StudyTrack.service.GoalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    GoalService goalService;

    @PostMapping("/save")
    public ResponseEntity<?> saveGoal(@RequestBody @Valid GoalRequestDTO goalRequestDTO){
        try {
            Goal newGoal = this.goalService.saveGoal(goalRequestDTO);
            GoalResponseDTO goalResponseDTO  = Mapper.toGoalDTO(newGoal);
            return ResponseEntity.ok(goalResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
