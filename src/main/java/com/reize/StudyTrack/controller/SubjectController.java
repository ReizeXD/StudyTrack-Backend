package com.reize.StudyTrack.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reize.StudyTrack.dto.mapper.Mapper;
import com.reize.StudyTrack.dto.subject.SubjectDTO;
import com.reize.StudyTrack.entity.Subject;
import com.reize.StudyTrack.repository.SubjectRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/subjects")
public class SubjectController {

    
    private final SubjectRepository subjectRepository;

    @GetMapping
    public ResponseEntity<?> findAll(){
        try {
            List<Subject> subjects= this.subjectRepository.findAll();
            List<SubjectDTO> subjectDTOs = Mapper.toListSubjectDTO(subjects);
            return ResponseEntity.ok(subjectDTOs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
