package com.register.app.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.register.app.model.Course;
import com.register.app.service.CourseService;

@Component
public class CourseFormatter implements Formatter<Course> {
	@Autowired
	CourseService courseService;

	@Override
	public String print(Course arg0, Locale arg1) {

		return arg0.getName();
	}

	@Override
	public Course parse(String arg0, Locale arg1) throws ParseException {
		Course newcourse = new Course();
		//newcourse.setCoursename(arg0);
		newcourse.setName(arg0);
		newcourse.setCourseNum(arg0);
		// return courseService.findByCourseNumber(arg0);
		return newcourse;
	}

}
