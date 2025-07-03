package com.reize.StudyTrack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.goal.GoalRequestDTO;
import com.reize.StudyTrack.dto.goal.GoalResponseDTO;
import com.reize.StudyTrack.dto.goal.GoalUpdateDTO;
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
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable Long id,@RequestBody @Valid GoalUpdateDTO goalUpdateDTO){
        try {
            Goal updateGoal= this.goalService.updateGoal(id, goalUpdateDTO);
            GoalResponseDTO goalResponseDTO = Mapper.toGoalDTO(updateGoal);
            return ResponseEntity.ok(goalResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<Goal> goals=this.goalService.findAll();
            List<GoalResponseDTO> goalResponseDTO = Mapper.toListGoalDTO(goals);
            return ResponseEntity.ok(goalResponseDTO); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try {
            Goal goal= this.goalService.findById(id);
            GoalResponseDTO goalResponseDTO = Mapper.toGoalDTO(goal);
            return ResponseEntity.ok(goalResponseDTO); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/findActive")
    public ResponseEntity<?> findAllActive(){
        try {
            List<Goal> goals=this.goalService.findAllActive();
            List<GoalResponseDTO> goalResponseDTO = Mapper.toListGoalDTO(goals);
            return ResponseEntity.ok(goalResponseDTO); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
    }
}
