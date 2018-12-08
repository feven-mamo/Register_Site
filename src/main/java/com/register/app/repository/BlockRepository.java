package com.register.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.register.app.model.Block;
import com.register.app.model.Prerequisite;


public interface BlockRepository  extends CrudRepository<Block,Integer> {
	List<Block> findAllByCourseNum(String courseNum);
}
