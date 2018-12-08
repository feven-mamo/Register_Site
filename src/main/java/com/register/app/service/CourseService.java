package com.register.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.Course;
import com.register.app.model.User;
import com.register.app.repository.CourseRepository;

@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepository courseRepository;
	
//	public List<Course> getAllCourses(){
//		List<Course> courses = new ArrayList<Course>();
//		for(Course course : courseRepository.findAll()) {
//			courses.add(course);
//		}
//		
//		return courses;
//	}
	
	public Course getCourseById(String courseNum)
	{
		return courseRepository.findById(courseNum).orElse(null);
	}
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	public List<Course> getAllCourses() {

		return (List<Course>) courseRepository.findAll();
	}
	public void deleteCourseByCourseNumber(String cnumber) {
		courseRepository.deleteByCourseNumber(cnumber);
	}
	
	public Course editCourseByCourseNumber(String cnumber) {
		return courseRepository.editByCourseNumber(cnumber);

	}
	public Course findByCourseNumber(String cnumber) {
		return courseRepository.findByCourseNumber(cnumber);

	}
	
}
