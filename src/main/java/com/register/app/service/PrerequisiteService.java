package com.register.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.register.app.model.Prerequisite;
import com.register.app.repository.PrerequisiteRepository;

@Service
@Transactional
public class PrerequisiteService {

	@Autowired
	PrerequisiteRepository prerequisiteRepository;
	public void savePreCourse(Prerequisite course) {
		prerequisiteRepository.save(course);
	}

	public List<Prerequisite> getAllPrerequisites() {		

		return (List<Prerequisite>)prerequisiteRepository.findAll();
	}

	public void deletePreCourseById(int id) {
		prerequisiteRepository.deleteById(id);
	}

	public Prerequisite editPreCourse(int id) {
		return prerequisiteRepository.findById(id).orElse(null);

	}
	public List<Prerequisite> getByCourseNum(String courseNum) {
		return    prerequisiteRepository.findAllByCourseNumberCourseNum(courseNum);

	}
}
