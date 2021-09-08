package epsilongtmyon.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	/*
	 * WebMvcAutoConfiguration#mvcValidatorから呼ばれる
	 * ここでSpringのValidatorを返すとそれが使われる
	 *
	 * ここの結果がValidatorAdapter.get で使われる
	 * nullの場合でBean Validationがクラスパスに通っていれば
	 * LocalValidatorFactoryBeanが使われるみたい
	 * (それがBean登録されるのは ValidationAutoConfiguration かな？)
	 * SpringValidatorAdapterにラップされて ValidatorAdapter でさらにラップされてる？
	 * SpringのValidatorとBeanValidationのValidatorを橋渡ししてる感じかな
	 *
	 */
	@Override
	public Validator getValidator() {
		return new MyValidator();
	}

	//もしくはSmartValidator
	public static class MyValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return true;
		}

		@Override
		public void validate(Object target, Errors errors) {
			//errors はBindingResultかな？
			// これが呼ばれる
			Thread.dumpStack();
		}

	}
}
