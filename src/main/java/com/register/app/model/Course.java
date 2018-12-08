package com.register.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.register.app.validator.CourseCode;

@Entity
@Table(name="course")
public class Course {

	@Id 
	@Column(name="course_number")
	@CourseCode
	private String courseNum;
	
	@Column(name="name")
	@NotNull(message="Course name must have a value")
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id", nullable = true)
	private Course prerequisite;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "prerequisite")
	private List<Course> c_number;
	boolean isprerequisite;
	
	public Course getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(Course prerequisite) {
		this.prerequisite = prerequisite;
	}
	public boolean isIsprerequisite() {
		return isprerequisite;
	}
	public void setIsprerequisite(boolean isprerequisite) {
		this.isprerequisite = isprerequisite;
	}
	public Course()
	{
		
	}
	public Course(String courseName, String courseNum)
	{
		super();
		this.courseNum=courseNum;
		this.name=courseName;
	}
	public String getCourseNum() {
		return courseNum;
	}
	
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
}
