package com.fpoly.teacher_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fpoly.teacher_management.model.Teacher;
import com.fpoly.teacher_management.model.TeacherType;
import com.fpoly.teacher_management.entity.RequestTeacher;
import com.fpoly.teacher_management.model.EducationLevel;
import com.fpoly.teacher_management.service.TeacherService;
import com.fpoly.teacher_management.service.TeacherTypeService;
import com.fpoly.teacher_management.service.EducationLevelService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherTypeService teacherTypeService;

    @Autowired
    private EducationLevelService educationLevelService;

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody RequestTeacher requestTeacher) {
        try {
        	System.out.println(requestTeacher);
        	if (requestTeacher.getEducationLevelId() instanceof Integer) {
        	    System.out.println("EducationLevelId is an Integer");
        	} 
        	System.out.println(requestTeacher.getTeacherTypeId());
        	System.out.println(requestTeacher.getEducationLevelId());

            // Convert IDs to actual entities
            Optional<TeacherType> teacherTypeOpt = teacherTypeService.getTeacherTypeById(requestTeacher.getTeacherTypeId());
            Optional<EducationLevel> educationLevelOpt = educationLevelService.getEducationLevelById(requestTeacher.getEducationLevelId());

                TeacherType teacherType = teacherTypeOpt.get();
                EducationLevel educationLevel = educationLevelOpt.get();

                // Create a new Teacher entity
                Teacher teacher = new Teacher();
                teacher.setTeacherId(requestTeacher.getTeacherId());
                teacher.setFirstName(requestTeacher.getFirstName());
                teacher.setLastName(requestTeacher.getLastName());
                teacher.setTeacherType(teacherType);
                teacher.setEducationLevel(educationLevel);
                teacher.setImage(requestTeacher.getImage());
                teacher.setBaseSalary(requestTeacher.getBaseSalary());
                teacher.setStartDate(requestTeacher.getStartDate());

                Teacher savedTeacher = teacherService.saveTeacher(teacher);
                return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
           
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable String id, @RequestBody RequestTeacher teacherDetails) {
        try {
            // Find the existing teacher by ID
            Teacher existingTeacher = teacherService.getTeacherById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Teacher not found"));

            // Ensure teacherType and educationLevel are provided in the request
            if (teacherDetails.getTeacherTypeId() == null || teacherDetails.getEducationLevelId() == null) {
                return ResponseEntity.badRequest().body(null);
            }

            // Fetch the related entities (TeacherType, EducationLevel)
            TeacherType teacherType = teacherTypeService.getTeacherTypeById(teacherDetails.getTeacherTypeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Teacher Type ID"));

            EducationLevel educationLevel = educationLevelService.getEducationLevelById(teacherDetails.getEducationLevelId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Education Level ID"));

            // Update the existing teacher's details
            existingTeacher.setFirstName(teacherDetails.getFirstName());
            existingTeacher.setLastName(teacherDetails.getLastName());
            existingTeacher.setTeacherType(teacherType);
            existingTeacher.setEducationLevel(educationLevel);
            existingTeacher.setImage(teacherDetails.getImage());
            existingTeacher.setBaseSalary(teacherDetails.getBaseSalary());
            existingTeacher.setStartDate(teacherDetails.getStartDate());

            // Save the updated teacher
            Teacher updatedTeacher = teacherService.saveTeacher(existingTeacher);

            return ResponseEntity.ok(updatedTeacher);
        } catch (ResponseStatusException e) {
            // Catch specific exceptions and respond with appropriate status
            return ResponseEntity.status(e.getStatusCode()).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable String id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/byLastName")
    public List<Teacher> getTeachersByLastName(@RequestParam String lastName) {
        return teacherService.getTeachersByLastName(lastName);
    }

    @GetMapping("/search/byTeacherType")
    public List<Teacher> getTeachersByTeacherType(@RequestParam Integer teacherType) {
        return teacherService.getTeachersByTeacherType(teacherType);
    }

    @GetMapping("/search/byEducationLevel")
    public List<Teacher> getTeachersByEducationLevel(@RequestParam Integer educationLevel) {
        return teacherService.getTeachersByEducationLevel(educationLevel);
    }
}
