package com.fpoly.teacher_management.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.teacher_management.model.EducationLevel;
import com.fpoly.teacher_management.repository.EducationLevelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EducationLevelService {

    @Autowired(required =  false)
    private EducationLevelRepository educationLevelRepository;

    public List<EducationLevel> getAllEducationLevels() {
        return educationLevelRepository.findAll();
    }

    public Optional<EducationLevel> getEducationLevelById(Integer id) {
        return educationLevelRepository.findById(id);
    }

    public EducationLevel saveEducationLevel(EducationLevel educationLevel) {
        return educationLevelRepository.save(educationLevel);
    }

    public void deleteEducationLevel(Integer id) {
        educationLevelRepository.deleteById(id);
    }
}

