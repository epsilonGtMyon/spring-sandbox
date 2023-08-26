package epsilongtmyon.lib.autoconfigure.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import epsilongtmyon.lib.autoconfigure.condition.ConditionalOnEnvContains2;

@AutoConfiguration
@ConditionalOnEnvContains2
public class EnvContains2AutoConfiguration {

	@Bean
	EnvContains2Hello envContains2Hello() {
		return new EnvContains2Hello();
	}

	public static class EnvContains2Hello {

		public String hello() {
			return getClass().getSimpleName() + " hello";
		}
	}
}
