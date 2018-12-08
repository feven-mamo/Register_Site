package com.register.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.Block;
import com.register.app.model.Course;
import com.register.app.repository.BlockRepository;
import com.register.app.repository.CourseRepository;

@Service
@Transactional
public class BlockService {

	@Autowired
	private BlockRepository blockRepository;
	
	public List<Block> getAllBlocksByCourseNum(String courseNum){
		List<Block> blocks = new ArrayList<Block>();
		for(Block block : blockRepository.findAllByCourseNum(courseNum)) {
			blocks.add(block);
		}
		
		return blocks;
	}
	public Block getBlockById(int id)
	{
		return blockRepository.findById(id).orElse(null);
	}
	public List<Block> getAllBlocks() {
		// TODO Auto-generated method stub
		return (List<Block>) blockRepository.findAll();
	}
	
	public void saveBlockCourse(Block bcourse) {
		blockRepository.save(bcourse);
	}

	public List<Block> getAllBlockCourse() {	
		return (List<Block>)blockRepository.findAll();
	}

	public void deleteBlockCourseById(int id) {
		blockRepository.deleteById(id);
	}

	public Block editBlockCourse(int id) {
		return blockRepository.findById(id).orElse(null);

	}
}
