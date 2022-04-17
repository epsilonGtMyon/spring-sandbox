package app.sandbox33.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Sandbox33Validator.class)
public @interface Sandbox33Annotation {

	String message() default "xxx.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
