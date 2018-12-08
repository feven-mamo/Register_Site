package com.register.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.register.app.model.User;

public interface UserRepository extends CrudRepository<User,Integer>{
	public User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
}
