package epsilongtmyon.common;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class MyControllerAdvice {

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		//ここからでもValidatorを追加することができる。
		dataBinder.addValidators(new MyValidator2());
	}

	public static class MyValidator2 implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return true;
		}

		@Override
		public void validate(Object target, Errors errors) {
			//BindingResult r = (BindingResult) errors;
			//r.addError(new ObjectError("aaa", "だめ"));
		}

	}
}
