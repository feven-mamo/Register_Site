package com.register.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.register.app.model.Block;
import com.register.app.model.Course;
import com.register.app.model.Faculty;
import com.register.app.service.BlockCourseService;
import com.register.app.service.CourseService;
import com.register.app.service.FacultyService;
import com.register.app.service.SecurityService;
import com.register.app.service.UserService;

@Controller
public class BlockController {
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	UserService userService;
	@Autowired
	BlockCourseService blockCourseService;
	
	@Autowired
	FacultyService facultyService;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping(value = "/show-blocks")
	public String BlockCourseList(Model model, HttpServletRequest request) {		
		model.addAttribute("blockCourses", blockCourseService.getAllBlockCourse());
		model.addAttribute("role", securityService.getUserRole());
		return "blockcourse";
	}
	@RequestMapping(value = "/add-block", method = RequestMethod.GET)
	public String showAddBlockCourseForm(@ModelAttribute("blockcourse") Block blockcourse, Model model) {
		List<Course> blocks = courseService.getAllCourses();
		List<Faculty> faculities=facultyService.getAllFaculities();
		model.addAttribute("blocks", blocks);
		model.addAttribute("faculities", faculities);
		model.addAttribute("role", securityService.getUserRole());
		return "addblockcourse";
	}

	@RequestMapping(value = "/add-block", method = RequestMethod.POST)
	public String addBlockCourse(@Valid @ModelAttribute("blockcourse") Block blockcourse, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().get(0));
			model.addAttribute("role", securityService.getUserRole());
			return "addblockcourse";
		}
		//System.out.println("======>" + blockcourse);
		Block savedblock = blockCourseService.saveBlockCourse(blockcourse);
		redirectAttribute.addFlashAttribute(savedblock);
		model.addAttribute("savedblock", savedblock);
		model.addAttribute("role", securityService.getUserRole());
		return "redirect:/show-blocks";
	}
}
