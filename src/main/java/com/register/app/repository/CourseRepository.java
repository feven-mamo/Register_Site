package com.register.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.register.app.model.Course;


public interface CourseRepository extends CrudRepository<Course,String> {
	@Modifying
	@Query(value = "DELETE  FROM Course course WHERE course.courseNum=:cnumber")
	public void deleteByCourseNumber(@Param("cnumber") String cnumber);

	@Query(value = "SELECT course FROM Course course WHERE course.courseNum=:cnumber")
	public Course editByCourseNumber(@Param("cnumber") String cnumber);

	@Query(value = "SELECT course FROM Course course WHERE course.courseNum=:cnumber")
	public Course findByCourseNumber(@Param("cnumber") String cnumber);
	

	
	
}
