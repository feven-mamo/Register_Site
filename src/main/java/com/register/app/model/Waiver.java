package com.register.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "waiver")
public class Waiver {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Valid
	@OneToOne
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;
	
	@Valid
	@OneToOne
	@JoinColumn(name = "course_number", nullable = false)
	private Course course;

	private String status = "Pending";

	public Waiver() {
		super();
	}

	public Waiver(Student student, Course course, String status) {
		super();
		this.student = student;
		this.course = course;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Waiver [waiverId=" + id + ", student=" + student + ", course=" + course + ", status=" + status + "]";
	}

}
