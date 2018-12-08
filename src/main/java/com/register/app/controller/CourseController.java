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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.register.app.model.Course;
import com.register.app.service.CourseService;
import com.register.app.service.SecurityService;

@Controller
public class CourseController {


	// space trimmer
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	CourseService courseService;

	@Autowired
	private SecurityService securityService;

	@RequestMapping(value = "/add-course", method = RequestMethod.GET)
	public String showAddCourseForm(@ModelAttribute("course") Course course, Model model) {
		List<Course> precourses =courseService.getAllCourses();
		model.addAttribute("precourses", precourses);
		model.addAttribute("role", securityService.getUserRole());
		return "addcourse";
	}

	@RequestMapping(value = "/add-course", method = RequestMethod.POST)
	public String addCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult,
			HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.getAllErrors().get(0));
			model.addAttribute("role", securityService.getUserRole());
			return "addcourse";
		}
		System.out.println("======>" + course);
		Course savedcourse = courseService.saveCourse(course);
		redirectAttribute.addFlashAttribute(savedcourse);
		model.addAttribute("savedcourse", savedcourse);
		model.addAttribute("role", securityService.getUserRole());
		return "redirect:/show-courses";
	}

	@GetMapping("/show-courses")
	public String showAllCourse(HttpServletRequest request, Model model) {
		model.addAttribute("courses", courseService.getAllCourses());
		System.out.println("========>>" + courseService.findByCourseNumber("CS101"));
		model.addAttribute("role", securityService.getUserRole());
		return "courselist";
	}

	@RequestMapping("/delete-course")
	public String deleteCourse(@RequestParam String coursenumber, HttpServletRequest request, Model model) {
		courseService.deleteCourseByCourseNumber(coursenumber);
		request.setAttribute("courses", courseService.getAllCourses());
		model.addAttribute("role", securityService.getUserRole());
		return "courselist";
	}

	@RequestMapping("/edit-course")
	public String editCourse(@RequestParam String coursenumber, HttpServletRequest request, Model model) {
		List<Course> precourses = courseService.getAllCourses();
		model.addAttribute("precourses", precourses);
		request.setAttribute("course", courseService.editCourseByCourseNumber(coursenumber));
		return "updatecourse";
	}
	@GetMapping("/show-coursespre")
	public String showAllPreCourse(HttpServletRequest request,Model model) {
		model.addAttribute("coursespre",courseService.getAllCourses());
		model.addAttribute("role", securityService.getUserRole());
		return "courseprelist";
	}
}
