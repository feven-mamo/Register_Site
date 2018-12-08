package com.register.app.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode,String> {

	private String coursePrefix;
	@Override
	public void initialize(CourseCode theCourseCode) {
		coursePrefix=theCourseCode.value();	
	}
	@Override
	public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
		//theCode is the actual course code the user enters
		//constraintValidatorContext=is to give additional error messages
		//spring mvc will call the isValid method
		boolean result;
		if(theCode!=null)
		{
			result=theCode.startsWith(coursePrefix);
			
		}
		else {
			result=true;//the course code is not a required field
			//if the user did not enter anything it will not display error message
		}
		return result;
	}

}
