package epsilongtmyon.app.common.validation.validator;

import epsilongtmyon.app.common.validation.ValueValidator;
import epsilongtmyon.app.common.validation.annotation.HalfAlphaNum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class HalfAlphaNumValidator implements ConstraintValidator<HalfAlphaNum, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ValueValidator.validateHalfAlphaNum(value);
	}

}
