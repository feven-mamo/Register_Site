package com.register.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="block_course")
public class Block {

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="course_number")
	private String courseNum;
	@NotNull(message="block is required")
	private String block;
	
	@OneToOne
	@JoinColumn(name = "professor",nullable = false)
	private Faculty professor;
	@Min(value=20,message="minimum value must be 20")
	@Max(value=25,message="minimum value must be 20")
	private int capacity;
	
	private int enrolled;
	 
	private int seats;
	
	@ManyToMany(mappedBy="blocks")
	private List<Student> students;
	
	public Block()
	{
		
	}
	public Block(String block, Faculty professor,int capacity,int enrolled,int seats)
	{
		this.block=block;
		this.professor=professor;
		this.capacity=capacity;
		this.enrolled=enrolled;
		this.seats=seats;
	}
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public Faculty getProfessor() {
		return professor;
	}
	public void setProfessor(Faculty professor) {
		this.professor = professor;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(int enrolled) {
		this.enrolled = enrolled;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public int getId() {
		return id;
	}
	
	
}
