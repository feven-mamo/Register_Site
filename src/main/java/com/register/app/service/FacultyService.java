package com.register.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.Faculty;
import com.register.app.repository.FacultyRepository;

@Service
@Transactional
public class FacultyService {
	@Autowired
	private FacultyRepository facultyRepository;

	public void saveFaculty(Faculty faculty) {
		facultyRepository.save(faculty);
	}

	public List<Faculty> getAllFaculities() {

		return (List<Faculty>) facultyRepository.findAll();
	}

	public void deleteFaculity(int id) {
		facultyRepository.deleteById(id);
	}

	public Faculty findByID(int id) {
		return facultyRepository.findById(id).get();

	}
}
