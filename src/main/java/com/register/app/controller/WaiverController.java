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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.register.app.model.Course;
import com.register.app.model.Student;
import com.register.app.model.User;
import com.register.app.model.Waiver;
import com.register.app.service.CourseService;
import com.register.app.service.SecurityService;
import com.register.app.service.StudentService;
import com.register.app.service.UserService;
import com.register.app.service.WaiverService;

@Controller
public class WaiverController {

	// space trimmer
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@Autowired
	WaiverService waiverService;

	@Autowired
	CourseService courseService;

	@Autowired
	StudentService studentService;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/approve-waivers")
	public String getApproveWaiver(HttpServletRequest request, @ModelAttribute Waiver waiver,
			BindingResult bindingResult, Model model) {
			
		request.setAttribute("waivers", waiverService.showAllWaivers());
		model.addAttribute("waiver", waiver);
		model.addAttribute("role", securityService.getUserRole());
		return "approve_waivers";

	}
	
	@RequestMapping("/waive")
	public String waiverApplication(HttpServletRequest request, @ModelAttribute Waiver waiver,
			BindingResult bindingResult, Model model) {

		List<Course> courses = courseService.getAllCourses();
		model.addAttribute("waiver", waiver);
		model.addAttribute("courses", courses);
		model.addAttribute("role", securityService.getUserRole());
		return "request_waiver";
	}

	@PostMapping("/waive")
	public String saveWaiverRequest(@Valid @ModelAttribute Waiver waiver, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) {
		String username = securityService.findLoggedInUsername();

		System.out.println("user name:" + username);
		System.out.println("user role:" + securityService.getUserRole());

		User user = userService.findByUsername(username);
		System.out.println("user id" + user.getUser_id());
		Student student = studentService.getStudentbyUserId(user.getUser_id());	
		waiver.setStudent(student);

		waiver.setCourse(courseService.getCourseById(waiver.getCourse().getCourseNum()));

		Waiver savedWaiver = waiverService.saveWaiver(waiver);
		
		model.addAttribute("savedWaiver", savedWaiver);
		model.addAttribute("role", securityService.getUserRole());
		return "redirect:/view";

		
	}

	@RequestMapping("/view")
	public String viewWaivers(HttpServletRequest request, Model model) {
		
		String username = securityService.findLoggedInUsername();
		System.out.println("user name:" + username);
		System.out.println("user role:" + securityService.getUserRole());

		User user = userService.findByUsername(username);
		System.out.println("user id" + user.getUser_id());
		
		Student student = studentService.getStudentbyUserId(user.getUser_id());	
		request.setAttribute("waivers", waiverService.showAllWaiverByID(student.getStudentid()));
		model.addAttribute("role", securityService.getUserRole());
		return "view_waivers";
	}

	@RequestMapping("/delete-waiver")
	public String deleteUser(@RequestParam int id, HttpServletRequest request, Model model) {
		waiverService.deleteWaiver(id);
		request.setAttribute("users", waiverService.showAllWaivers());
		request.setAttribute("mode", "ALL_USERS");
		model.addAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}
	
	@RequestMapping("/edit-waiver")
	public String editUser(@RequestParam int id,HttpServletRequest request, Model model) {
		request.setAttribute("mode", "MODE_UPDATE");
		model.addAttribute("role", securityService.getUserRole());
		return "welcomepage";
	}

}
