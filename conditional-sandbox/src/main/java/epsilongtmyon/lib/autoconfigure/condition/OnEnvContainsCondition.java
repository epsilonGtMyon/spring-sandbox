package epsilongtmyon.lib.autoconfigure.condition;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * {@link ConditionalOnEnvContains} の判定を行う
 */
public class OnEnvContainsCondition extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		final Map<String, Object> attributes = metadata
				.getAnnotationAttributes(ConditionalOnEnvContains.class.getName());

		final String envKey = (String) attributes.get("envKey");
		final String envValue = (String) attributes.get("envValue");

		final PropertyResolver resolver = context.getEnvironment();

		return isMatch(envKey, envValue, resolver)
				? ConditionOutcome.match()
				: ConditionOutcome.noMatch("value is false");
	}

	private boolean isMatch(String envKey, String envValue, PropertyResolver resolver) {
		Set<String> currentEnvVales = Arrays.stream(resolver.getProperty(envKey, "").split(","))

				.map(String::trim)

				.collect(Collectors.toSet());

		return currentEnvVales.contains(envValue);

	}

}
