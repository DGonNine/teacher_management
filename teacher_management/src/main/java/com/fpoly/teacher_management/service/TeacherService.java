package com.fpoly.teacher_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpoly.teacher_management.model.Teacher;
import com.fpoly.teacher_management.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(String teacherId) {
        return teacherRepository.findById(teacherId);
    }

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(String teacherId) {
        teacherRepository.deleteById(teacherId);
    }

    public List<Teacher> getTeachersByLastName(String lastName) {
        return teacherRepository.findByLastName(lastName);
    }

    public List<Teacher> getTeachersByTeacherType(Integer teacherType) {
        return teacherRepository.findByTeacherType_Id(teacherType);
    }

    public List<Teacher> getTeachersByEducationLevel(Integer educationLevel) {
        return teacherRepository.findByEducationLevel_Id(educationLevel);
    }
}
