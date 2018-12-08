package com.register.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.register.app.model.Block;
import com.register.app.model.Course;
import com.register.app.model.Prerequisite;
import com.register.app.model.Student;
import com.register.app.model.User;
import com.register.app.repository.PrerequisiteRepository;
import com.register.app.service.BlockService;
import com.register.app.service.CourseService;
import com.register.app.service.PrerequisiteService;
import com.register.app.service.SecurityService;
import com.register.app.service.StudentService;
import com.register.app.service.UserService;

@Controller
public class StudentController {

	@Autowired
	private CourseService courseService;
	@Autowired
	private UserService userService;
	@Autowired
	private PrerequisiteService prerequisiteService;
	@Autowired
	private BlockService blockService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SecurityService securityService;

	@GetMapping("/addcourse")
	public String add(HttpServletRequest request, @ModelAttribute Course course, Model model,
			BindingResult bindingResult) {

		List<Course> courses = courseService.getAllCourses();
		model.addAttribute("courses", courses);
		model.addAttribute("role", securityService.getUserRole());
		return "addpreference";
	}

	@GetMapping("/showstatus")
	public String showStatus(HttpServletRequest request, @ModelAttribute Course course, Model model,
			BindingResult bindingResult) {

		String username = securityService.findLoggedInUsername();

		System.out.println("user name:" + username);
		System.out.println("user role:" + securityService.getUserRole());

		User user = userService.findByUsername(username);
		System.out.println("user id" + user.getUser_id());
		Student student = studentService.getStudentbyUserId(user.getUser_id());
		System.out.println("Student : " + student.getStudentid());
		List<Block> blocks = student.getBlocks();

		// get pending requests by block and student id

		model.addAttribute("blocks", blocks);

		model.addAttribute("role", securityService.getUserRole());
		return "showpreference";
	}

	@RequestMapping(value = "/predcourse", method = RequestMethod.PUT)
	public @ResponseBody String selectCourse(HttpServletRequest request, @RequestBody String courseName,
			@RequestParam("courseNum") String courseNum, Model model, BindingResult bindingResult) {
		String hi = "hiiiii";
		Course courseDetail = courseService.getCourseById(courseNum);
		model.addAttribute("precourse", courseDetail);
		return hi;
	}

	@RequestMapping(value = "/predcourse", method = RequestMethod.GET)
	public @ResponseBody List<Prerequisite> selectCourseTest(@RequestParam("courseNum") String courseNum,
			HttpServletRequest request) {

		List<Prerequisite> precourses = prerequisiteService.getByCourseNum(courseNum);
		return precourses;
	}

	@RequestMapping(value = "/showblock", method = RequestMethod.GET)
	public @ResponseBody List<Block> showBlock(@RequestParam("courseNum") String courseNum,
			HttpServletRequest request) {

		List<Block> blocks = blockService.getAllBlocksByCourseNum(courseNum);
		System.out.println(blocks);
		return blocks;
	}

	@RequestMapping(value = "/savePreference", method = RequestMethod.POST)
	public @ResponseBody Student SavePreference(@RequestParam("courseNum") String courseNum,
			@RequestBody String blockid, HttpServletRequest request) {
		// System.out.println(courseNum +" "+ blockid );

		Student student = studentService.getStudentbyId(4);
		int bid = Integer.parseInt(blockid);

		Block block = blockService.getBlockById(bid);
		// System.out.println("block: "+block);
		student.addBlock(block);
		// System.out.println("student :"+student);
		Student updatedstudent = studentService.updateStudent(student);
	
		return updatedstudent;
	}

	@GetMapping("/approvepre")
	public String showAllUsers(HttpServletRequest request, @ModelAttribute Student student, Model model,
			BindingResult bindingResult) {
		
		//get all requests by all students
		
		List<Student> students = studentService.getAllRequests();

		model.addAttribute("students", students);

		model.addAttribute("role", securityService.getUserRole());
	
		return "approvepreference";
	}

}
