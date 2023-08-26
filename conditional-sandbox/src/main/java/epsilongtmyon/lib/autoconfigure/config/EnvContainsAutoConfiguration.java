package epsilongtmyon.lib.autoconfigure.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import epsilongtmyon.lib.autoconfigure.condition.ConditionalOnEnvContains;

@AutoConfiguration
@ConditionalOnEnvContains(envKey = "enable.configs", envValue = "EnvContainsAutoConfiguration")
public class EnvContainsAutoConfiguration {

	@Bean
	EnvContainsHello envContainsHello() {
		return new EnvContainsHello();
	}

	public static class EnvContainsHello {

		public String hello() {
			return getClass().getSimpleName() + " hello";
		}
	}
}
