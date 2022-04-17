package app.sandbox33.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Sandbox33Validator implements ConstraintValidator<Sandbox33Annotation, String> {

	private final Sandbox33Xxx sandbox33Xxx;

	//spring経由でConstraintValidator がインスタンス化されるようなので
	//Beanをinjectすることができる。
	public Sandbox33Validator(Sandbox33Xxx sandbox33Xxx) {
		this.sandbox33Xxx = sandbox33Xxx;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return sandbox33Xxx.validate(value);
	}

}
