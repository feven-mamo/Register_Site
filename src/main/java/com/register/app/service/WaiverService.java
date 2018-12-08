package com.register.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.User;
import com.register.app.model.Waiver;
import com.register.app.repository.StudentRepository;
import com.register.app.repository.WaiverRepository;

@Service
@Transactional
public class WaiverService {

	@Autowired
	WaiverRepository waiverRepository;
	
	@Autowired
	private StudentRepository studentRepository;

	public Waiver saveWaiver(Waiver waiver) {
		return waiverRepository.save(waiver);
	}

	public List<Waiver> showAllWaiverByID(int studentId) {

		return waiverRepository.findByStudent(studentRepository.findById(studentId).orElse(null));

	}
	
	public List<Waiver> showAllWaivers(){
		
		return (List<Waiver>) waiverRepository.findAll();
	}
//	public List<User> showAllUsers(){
//		List<User> users = new ArrayList<User>();
//		for(User user : userRepository.findAll()) {
//			users.add(user);
//		}
//		
//		return users;
//	}
	public void deleteWaiver(int id) {
		waiverRepository.deleteById(id);
	}
	

}
