package com.register.app.validator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {
	//default course code
	public String value() default "CS";
//default error message
	public String message() default "Course code must start with CS";
	//default group
	   public Class<?>[] groups() default {}; //Groups:can group related constraints
	//default payload
	  public Class<? extends Payload>[] payload() default {};                //Payloads: provide custom details about validation failure
}
