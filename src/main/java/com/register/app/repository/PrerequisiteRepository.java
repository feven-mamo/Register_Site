package com.register.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.register.app.model.Prerequisite;


@Repository
public interface PrerequisiteRepository extends CrudRepository <Prerequisite,Integer>{
	List<Prerequisite> findAllByCourseNumberCourseNum(String courseNum);
}
