package epsilongtmyon.lib.autoconfigure.condition;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

// Conditionを実装すれば良いが SpringBootConditionというベースのクラスがあるので利用する。
/**
 * {@link ConditionalOnBoolean} の判定を行う
 */
public class OnBooleanCondition extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionalOnBoolean.class.getName());

		boolean value = ((Boolean) attributes.get("value")).booleanValue();

		return value ? ConditionOutcome.match() : ConditionOutcome.noMatch("value is false");
	}

}
