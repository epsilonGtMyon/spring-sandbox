package app.section03.sandbox0303.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = Sandbox0303Validator.class)
public @interface Sandbox0303Annotation {

	String message() default "xxx.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
