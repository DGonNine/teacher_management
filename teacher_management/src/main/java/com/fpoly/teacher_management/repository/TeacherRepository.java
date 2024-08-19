package com.fpoly.teacher_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.teacher_management.model.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {
    
    // Custom query methods can be added here if needed
    List<Teacher> findByLastName(String lastName);

    List<Teacher> findByTeacherType_Id(Integer teacherTypeId);

    List<Teacher> findByEducationLevel_Id(Integer educationLevelId);
}
