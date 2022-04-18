package app.section03.sandbox0303.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Sandbox0303Validator implements ConstraintValidator<Sandbox0303Annotation, String> {

	private final Sandbox0303Xxx sandbox33Xxx;

	//spring経由でConstraintValidator がインスタンス化されるようなので
	//Beanをinjectすることができる。
	public Sandbox0303Validator(Sandbox0303Xxx sandbox33Xxx) {
		this.sandbox33Xxx = sandbox33Xxx;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return sandbox33Xxx.validate(value);
	}

}
