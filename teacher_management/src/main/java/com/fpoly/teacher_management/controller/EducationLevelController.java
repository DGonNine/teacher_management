package com.fpoly.teacher_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpoly.teacher_management.model.EducationLevel;
import com.fpoly.teacher_management.service.EducationLevelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/educationLevels")
@CrossOrigin(origins = "http://localhost:3000")
public class EducationLevelController {

    @Autowired
    private EducationLevelService educationLevelService;

// Lấy full
    @GetMapping
    public List<EducationLevel> getAllEducationLevels() {
    	System.out.println("vao dayy");
        return educationLevelService.getAllEducationLevels();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EducationLevel> getEducationLevelById(@PathVariable Integer id) {
        Optional<EducationLevel> educationLevel = educationLevelService.getEducationLevelById(id);
        return educationLevel.map(ResponseEntity::ok)
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EducationLevel createEducationLevel(@RequestBody EducationLevel educationLevel) {
        return educationLevelService.saveEducationLevel(educationLevel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EducationLevel> updateEducationLevel(@PathVariable Integer id, @RequestBody EducationLevel educationLevelDetails) {
        Optional<EducationLevel> educationLevel = educationLevelService.getEducationLevelById(id);
        if (educationLevel.isPresent()) {
//            educationLevelDetails.setId(id);
            EducationLevel updatedEducationLevel = educationLevelService.saveEducationLevel(educationLevelDetails);
            return ResponseEntity.ok(updatedEducationLevel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEducationLevel(@PathVariable Integer id) {
        educationLevelService.deleteEducationLevel(id);
        return ResponseEntity.noContent().build();
    }
}
