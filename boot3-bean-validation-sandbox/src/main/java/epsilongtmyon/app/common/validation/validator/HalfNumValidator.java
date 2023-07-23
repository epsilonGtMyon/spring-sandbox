package epsilongtmyon.app.common.validation.validator;

import epsilongtmyon.app.common.validation.ValueValidator;
import epsilongtmyon.app.common.validation.annotation.HalfNum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HalfNumValidator implements ConstraintValidator<HalfNum, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValueValidator.validateHalfNum(value);
	}

}
