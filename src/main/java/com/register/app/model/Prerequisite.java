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

@Entity
@Table(name = "prerequisite")
public class Prerequisite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_number")
	private Course courseNumber;
	@Column(name="prerequisite_course")
	private String precourse;

	public Prerequisite() {
	}

	public Course getCourse_number() {
		return courseNumber;
	}

	public void setCourse_number(Course course_number) {
		this.courseNumber = course_number;
	}

	public String getPrecourse() {
		return precourse;
	}

	public void setPrecourse(String precourse) {
		this.precourse = precourse;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Prerequisite [id=" + id + ", course_number=" + courseNumber + ", precourse=" + precourse + "]";
	}

	

}
