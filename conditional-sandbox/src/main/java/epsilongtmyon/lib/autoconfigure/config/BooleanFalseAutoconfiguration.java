package epsilongtmyon.lib.autoconfigure.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import epsilongtmyon.lib.autoconfigure.condition.ConditionalOnBoolean;

@AutoConfiguration
@ConditionalOnBoolean(false)
public class BooleanFalseAutoconfiguration {


	@Bean
	BooleanFalseHello booleanFalseHello() {
		return new BooleanFalseHello();
	}

	public static class BooleanFalseHello {

		public String hello() {
			return getClass().getSimpleName() + " hello";
		}
	}
}
