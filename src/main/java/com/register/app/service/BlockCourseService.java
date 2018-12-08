package com.register.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.Block;
import com.register.app.repository.BlockCourseRepository;

@Service
@Transactional
public class BlockCourseService {
	@Autowired
	private BlockCourseRepository blockCourseRepository;

	public Block saveBlockCourse(Block bcourse) {
		return blockCourseRepository.save(bcourse);
	}

	public List<Block> getAllBlockCourse() {	
		return (List<Block>)blockCourseRepository.findAll();
	}

	public void deleteBlockCourseById(int id) {
		blockCourseRepository.deleteById(id);
	}

	public Block editBlockCourse(int id) {
		return blockCourseRepository.findById(id).orElse(null);

	}
}
