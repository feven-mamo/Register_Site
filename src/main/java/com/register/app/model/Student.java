package com.register.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="student")
public class Student {

	@Id
	private int student_id;
	
	@Column(name="reg_number")
	private int regNum;
	@NotNull(message="Entry is required")
	private String entry;
	
	@NotNull(message="Track is required")
	private String track;
	
	@OneToOne
	@JoinColumn(name = "advisor",nullable = false)	
	private Faculty advisor;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToMany
	@JoinTable(name = "student_course",joinColumns=@JoinColumn(name="student_id"), inverseJoinColumns=@JoinColumn(name="block_id"))
	private List<Block> blocks;
	
	public void addBlock(Block block)
	{
		blocks.add(block);
	}
	public List<Block> getBlocks()
	{
		return blocks;
	}
	public Student()
	{
		
	}
	
	public Student(int regNum, String entry, String track, Faculty advisor, User user)
	{
		this.regNum=regNum;
		this.entry=entry;
		this.track=track;
		this.advisor=advisor;
		this.user=user;
	}
	public int getStudentid() {
		return student_id;
	}
	
	public int getRegNum() {
		return regNum;
	}
	public void setRegNum(int regNum) {
		this.regNum = regNum;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
	public Faculty getAdvisor() {
		return advisor;
	}
	public void setAdvisor(Faculty advisor) {
		this.advisor = advisor;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
