package com.reize.StudyTrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.mapper.Mapper;
import com.reize.StudyTrack.dto.progress.ProgressRequestDTO;
import com.reize.StudyTrack.dto.progress.ProgressUpdateDTO;
import com.reize.StudyTrack.entity.Progress;
import com.reize.StudyTrack.service.ProgressService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/progress")
public class ProgressController {
    
    
    private final ProgressService progressService;

    @PostMapping("/save")
    public ResponseEntity<?> saveProgress(@RequestBody @Valid ProgressRequestDTO progressRequestDTO){
        try {
            Progress progress = progressService.saveProgress(progressRequestDTO);
            ProgressRequestDTO progressResponseDTO = Mapper.toProgressDTO(progress);
            return ResponseEntity.ok(progressResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProgress (@PathVariable Long id, @RequestBody @Valid ProgressUpdateDTO progressUpdateDTO){
        try {
            Progress progress = progressService.updateProgress(id,progressUpdateDTO);
            ProgressRequestDTO progressResponseDTO = Mapper.toProgressDTO(progress);
            return ResponseEntity.ok(progressResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam Long id){
        try {
            List<Progress> progresses=this.progressService.findAll(id);
            List<ProgressRequestDTO> progressResponseDTO = Mapper.toListProgressDTO(progresses);
            return ResponseEntity.ok(progressResponseDTO); 
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
