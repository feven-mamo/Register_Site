package com.register.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.Student;
import com.register.app.model.User;
import com.register.app.repository.StudentRepository;

@Service
@Transactional
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public Student getStudentbyId(int studentid)
	{
		return studentRepository.findById(studentid).orElse(null);
	}
	public Student getStudentbyUserId(int userid)
	{
		return studentRepository.findByUserid(userid);
	}
	
	public Student updateStudent(Student student)
	{
		return studentRepository.save(student);
	}
	public List<Student> getAllRequests()
	{
		return (List<Student>)studentRepository.findAll();
	}
//	public List<Student> getAllRequestsByStudentId(int id)
//	{
//		return (List<Student>)studentRepository.fin;
//	}
}
