package com.register.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.register.app.model.Student;
import com.register.app.model.User;



public interface StudentRepository extends CrudRepository<Student,Integer> {
	
	
	
	@Query(value ="select s from Student s where s.user.user_id=?1")
	Student findByUserid(@Param("userid")int userid);
	
	@Query(value ="select s from Student s where s.user.user_id=?1")
	List<Student> findAllRequests(@Param("userid")int userid);
}
