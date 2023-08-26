package epsilongtmyon.lib.autoconfigure.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import epsilongtmyon.lib.autoconfigure.condition.ConditionalOnBoolean;

@AutoConfiguration
@ConditionalOnBoolean(true)
public class BooleanTrueAutoconfiguration {

	@Bean
	BooleanTrueHello booleanTrueHello() {
		return new BooleanTrueHello();
	}

	public static class BooleanTrueHello {

		public String hello() {
			return getClass().getSimpleName() + " hello";
		}
	}
}
