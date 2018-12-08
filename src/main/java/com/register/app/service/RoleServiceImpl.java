package com.register.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.register.app.model.Role;
import com.register.app.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
    private RoleRepository roleRepository;

	@Override
	public Role getUserRole(String name) {
		return roleRepository.findByName(name);
	}

}
