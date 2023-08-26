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
import org.springframework.core.type.ClassMetadata;

/**
 * {@link ConditionalOnEnvContains2} の判定を行う
 */
public class OnEnvContainsCondition2 extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		final Map<String, Object> attributes = metadata
				.getAnnotationAttributes(ConditionalOnEnvContains2.class.getName());

		final String envKey = (String) attributes.get("envKey");
		final String targetClassName = findTargetClass(metadata);

		final PropertyResolver resolver = context.getEnvironment();

		return isMatch(envKey, targetClassName, resolver)
				? ConditionOutcome.match()
				: ConditionOutcome.noMatch("value is false");
	}

	private boolean isMatch(String envKey, String targetClassName, PropertyResolver resolver) {
		Set<String> currentEnvVales = Arrays.stream(resolver.getProperty(envKey, "").split(","))

				.map(String::trim)

				.collect(Collectors.toSet());

		return currentEnvVales.contains(targetClassName);
	}

	private String findTargetClass(AnnotatedTypeMetadata metadata) {
		if (metadata instanceof ClassMetadata classMetadata) {
			return classMetadata.getClassName();
		}
		//		if (metadata instanceof MethodMetadata methodMetadata) {
		//			return methodMetadata.getReturnTypeName();
		//		}

		throw new IllegalStateException("unknown metadata " + metadata);
	}

}
