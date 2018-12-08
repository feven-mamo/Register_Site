package com.register.app.controller;



import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.register.app.model.User;
import com.register.app.service.SecurityService;
import com.register.app.service.UserService;

@Controller
public class ApplicationController {

	// space trimmer
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	UserService userService;
	
	@Autowired
    private SecurityService securityService;

	@ResponseBody
	@RequestMapping("/home")
	public String home() {

		return "hi tg";
	}

	@RequestMapping(value= {"/","/welcome"})
	public String welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
    	request.setAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}

	@RequestMapping("/register")
	public String registeration(HttpServletRequest request, @ModelAttribute User user, BindingResult bindingResult) {

		request.setAttribute("mode", "MODE_REGISTER");
		request.setAttribute("role", securityService.getUserRole());
		return "welcomepage";

	}

	@PostMapping("/save-user")
	public String registerUser(@Valid @ModelAttribute User user, BindingResult bindingResult, RedirectAttributes rd,
			HttpServletRequest request) {

		System.out.println("1==================> save-user");
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream()
			.peek(e -> System.out.println("e--> "+e.getDefaultMessage()))
			.count();
			System.out.println(user.toString());
			request.setAttribute("mode", "MODE_REGISTER");
			request.setAttribute("role", securityService.getUserRole());
			return "welcomepage";
		} else {
			// profile picture
			MultipartFile userImage = user.getImage();
			String rootDirectory = request.getSession().getServletContext().getRealPath("/");
			if (userImage != null && !userImage.isEmpty()) {
				try {
					User savedUser=userService.saveUser(user);
					
					rd.addFlashAttribute(savedUser);
					
					String msg = savedUser.getUsername()+ " is saved!!";
					request.setAttribute("msg", msg);
					System.out.println("2==================> save-user");
					
					userImage.transferTo(
							new File(rootDirectory + "resources\\images\\" + savedUser.getUser_id() + ".png"));
				} catch (Exception e) {
					throw new RuntimeException("User Image saving failed", e);
				}
			}
			
			//securityService.autologin(user.getUsername(), user.getPassword());
			//request.setAttribute("mode", "MODE_REGISTER");
			request.setAttribute("mode", "ALL_USERS");
			
			request.setAttribute("role", securityService.getUserRole());
			return "redirect:/show-users";
		}

	}

	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request, Model model) {
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		request.setAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userService.deleteMyUser(id);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		request.setAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}

	@RequestMapping("/edit-user")
	public String editUser(@RequestParam int id, HttpServletRequest request) {
		request.setAttribute("user", userService.editUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		request.setAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		request.setAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}

	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if (userService.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
			return "homepage";
		} else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			request.setAttribute("role", securityService.getUserRole());
			return "welcomepage";

		}
	}
}
