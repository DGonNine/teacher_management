package com.fpoly.teacher_management.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.time.LocalDate;

@Entity
@Table(name = "teacher")
public class Teacher {
	public Teacher() {
	}

	public Teacher(String teacherId, String lastName, String firstName, TeacherType teacherType,
			EducationLevel educationLevel, String image, int baseSalary, LocalDate startDate) {
		super();
		this.teacherId = teacherId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.teacherType = teacherType;
		this.educationLevel = educationLevel;
		this.image = image;
		this.baseSalary = baseSalary;
		this.startDate = startDate;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public TeacherType getTeacherType() {
		return teacherType;
	}

	public void setTeacherType(TeacherType teacherType) {
		this.teacherType = teacherType;
	}

	public EducationLevel getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(EducationLevel educationLevel) {
		this.educationLevel = educationLevel;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	@Id
	@Column(name = "teacher_id", unique = true)
	private String teacherId;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@ManyToOne
	@JoinColumn(name = "teacher_type")
	private TeacherType teacherType;

	@ManyToOne
	@JoinColumn(name = "education_level")
	private EducationLevel educationLevel;

	@Column(name = "image")
	private String image;

	@Column(name = "base_salary", nullable = false)
	private int baseSalary;

	@Column(name = "start_date", nullable = false)
	private LocalDate startDate;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.firstName;
	}

}
