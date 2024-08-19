package com.fpoly.teacher_management.repository;


import com.fpoly.teacher_management.model.EducationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationLevelRepository extends JpaRepository<EducationLevel, Integer> {
}
