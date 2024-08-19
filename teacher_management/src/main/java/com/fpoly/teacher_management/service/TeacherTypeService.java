package com.fpoly.teacher_management.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.teacher_management.model.TeacherType;
import com.fpoly.teacher_management.repository.TeacherTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherTypeService {

    @Autowired
    private TeacherTypeRepository teacherTypeRepository;

    public List<TeacherType> getAllTeacherTypes() {
        return teacherTypeRepository.findAll();
    }

    public Optional<TeacherType> getTeacherTypeById(Integer id) {
        return teacherTypeRepository.findById(id);
    }

    public TeacherType saveTeacherType(TeacherType teacherType) {
        return teacherTypeRepository.save(teacherType);
    }

    public void deleteTeacherType(Integer id) {
        teacherTypeRepository.deleteById(id);
    }
}
