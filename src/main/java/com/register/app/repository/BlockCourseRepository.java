package com.register.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.register.app.model.Block;

@Repository
public interface BlockCourseRepository extends CrudRepository <Block,Integer>{

}
