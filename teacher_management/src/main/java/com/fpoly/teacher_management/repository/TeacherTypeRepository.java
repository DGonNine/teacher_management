package com.fpoly.teacher_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpoly.teacher_management.model.TeacherType;

@Repository
public interface TeacherTypeRepository extends JpaRepository<TeacherType, Integer> {
    
    // Custom query methods can be added here if needed
}
