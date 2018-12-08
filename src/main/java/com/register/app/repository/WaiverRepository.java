package com.register.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.register.app.model.Student;
import com.register.app.model.Waiver;

public interface WaiverRepository extends CrudRepository<Waiver, Integer>{
	
	List<Waiver> findByStudent(Student student);

}
