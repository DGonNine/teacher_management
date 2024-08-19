package com.fpoly.teacher_management.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fpoly.teacher_management.model.TeacherType;
import com.fpoly.teacher_management.service.TeacherTypeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teacherTypes")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherTypeController {

    @Autowired
    private TeacherTypeService teacherTypeService;

    @GetMapping
    public List<TeacherType> getAllTeacherTypes() {
        return teacherTypeService.getAllTeacherTypes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherType> getTeacherTypeById(@PathVariable Integer id) {
        Optional<TeacherType> teacherType = teacherTypeService.getTeacherTypeById(id);
        return teacherType.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public TeacherType createTeacherType(@RequestBody TeacherType teacherType) {
        return teacherTypeService.saveTeacherType(teacherType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherType> updateTeacherType(@PathVariable Integer id, @RequestBody TeacherType teacherTypeDetails) {
        Optional<TeacherType> teacherType = teacherTypeService.getTeacherTypeById(id);
        if (teacherType.isPresent()) {
            teacherTypeDetails.setId(id);
            TeacherType updatedTeacherType = teacherTypeService.saveTeacherType(teacherTypeDetails);
            return ResponseEntity.ok(updatedTeacherType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacherType(@PathVariable Integer id) {
        teacherTypeService.deleteTeacherType(id);
        return ResponseEntity.noContent().build();
    }
}
