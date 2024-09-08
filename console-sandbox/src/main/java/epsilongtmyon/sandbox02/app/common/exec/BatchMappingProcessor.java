package epsilongtmyon.sandbox02.app.common.exec;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class BatchMappingProcessor implements ApplicationListener<ContextRefreshedEvent> {

	private final BatchMapping batchMapping;

	public BatchMappingProcessor(BatchMapping batchMapping) {
		super();
		this.batchMapping = batchMapping;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		final ApplicationContext context = event.getApplicationContext();

		String[] batchBeanNames = context.getBeanNamesForAnnotation(Batch.class);
		final Map<String, Object> map = new HashMap<>(batchBeanNames.length);
		for (String batchBeanName : batchBeanNames) {
			final Object bean = context.getBean(batchBeanName);

			final Batch annotation = bean.getClass().getAnnotation(Batch.class);
			final String batchId = annotation.value();

			if (map.containsKey(batchId)) {
				throw new IllegalStateException("batchId[" + batchId + "] is dupprecated.");
			}

			map.put(batchId, bean);
		}

		batchMapping.addAll(map);
	}

}
