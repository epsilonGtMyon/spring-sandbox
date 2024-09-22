package epsilongtmyon.sandbox02.app.common.exec.condition;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import epsilongtmyon.sandbox02.app.common.exec.annotation.Batch;

// ProfileConditionを参考

/**
 * バッチ処理の有効無効を判定
 */
public class BatchCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		final MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Batch.class.getName());

		final ApplicationArguments args = context.getBeanFactory().getBean(ApplicationArguments.class);
		final Set<String> batchIds = args.getOptionValues("batchId").stream().collect(Collectors.toSet());

		if (attrs != null) {
			for (Object value : attrs.get("value")) {
				if (batchIds.contains(value)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}
}
