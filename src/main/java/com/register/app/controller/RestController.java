package com.register.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.register.app.model.User;
import com.register.app.service.UserService;
@org.springframework.web.bind.annotation.RestController
public class RestController {


	@Autowired
	private UserService userService;
	@GetMapping("/register-site")
	public String home()
	{
		return "this is home page";
	}
	
}
