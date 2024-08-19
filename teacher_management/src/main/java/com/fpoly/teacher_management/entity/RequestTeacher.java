package com.fpoly.teacher_management.entity;

import java.time.LocalDate;

public class RequestTeacher {

    private String teacherId;
    private String firstName;

    private String lastName;

    private Integer teacherTypeId; // ID of TeacherType

    private Integer educationLevelId; // ID of EducationLevel

    private String image;

    private Integer baseSalary;

    private LocalDate startDate;

    // Constructors
    public RequestTeacher() {}

    public RequestTeacher(String teacherId, String firstName, String lastName, Integer teacherTypeId, Integer educationLevelId, String image, Integer baseSalary, LocalDate startDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.teacherTypeId = teacherTypeId;
        this.educationLevelId = educationLevelId;
        this.image = image;
        this.baseSalary = baseSalary;
        this.startDate = startDate;
        this.teacherId = teacherId;
    }

    public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacher_id) {
		this.teacherId = teacher_id;
	}

	// Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTeacherTypeId() {
        return teacherTypeId;
    }

    public void setTeacherTypeId(Integer teacherTypeId) {
        this.teacherTypeId = teacherTypeId;
    }

    public Integer getEducationLevelId() {
        return educationLevelId;
    }

    public void setEducationLevelId(Integer educationLevelId) {
        this.educationLevelId = educationLevelId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Integer baseSalary) {
        this.baseSalary = baseSalary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "RequestTeacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", teacherTypeId=" + teacherTypeId +
                ", educationLevelId=" + educationLevelId +
                ", image='" + image + '\'' +
                ", baseSalary=" + baseSalary +
                ", startDate=" + startDate +
                ", teacherId " + teacherId +
                '}';
    }
}
