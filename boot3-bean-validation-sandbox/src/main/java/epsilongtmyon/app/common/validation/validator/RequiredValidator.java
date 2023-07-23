package epsilongtmyon.app.common.validation.validator;

import epsilongtmyon.app.common.validation.ValueValidator;
import epsilongtmyon.app.common.validation.annotation.Required;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link Required}用のバリデーター
 */
public class RequiredValidator implements ConstraintValidator<Required, Object> {

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return ValueValidator.validateRequired(value);
	}

}
