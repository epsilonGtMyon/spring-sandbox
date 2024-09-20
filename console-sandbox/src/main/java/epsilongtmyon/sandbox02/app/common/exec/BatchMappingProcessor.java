package epsilongtmyon.sandbox02.app.common.exec;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.annotation.Batch;

/**
 * バッチのIDとBean名のマッピングを作成するクラス
 */
@Component
public class BatchMappingProcessor implements ApplicationListener<ContextRefreshedEvent> {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(BatchMappingProcessor.class);

	private final BatchMapping batchMapping;

	public BatchMappingProcessor(BatchMapping batchMapping) {
		super();
		this.batchMapping = batchMapping;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		final ApplicationContext context = event.getApplicationContext();

		final String[] batchBeanNames = context.getBeanNamesForAnnotation(Batch.class);
		final Map<String, BatchMetadata> map = new HashMap<>(batchBeanNames.length);

		for (String batchBeanName : batchBeanNames) {

			final Batch annotation = context.findAnnotationOnBean(batchBeanName, Batch.class);
			final String batchId = annotation.value();

			if (map.containsKey(batchId)) {
				throw new IllegalStateException("batchId[" + batchId + "] is dupprecated.");
			}

			Class<?> clazz = context.getType(batchBeanName);
			Map<Method, Set<Scheduled>> annotatedMethods = MethodIntrospector.selectMethods(clazz,
					(MethodIntrospector.MetadataLookup<Set<Scheduled>>) method -> {
						Set<Scheduled> scheduledAnnotations = AnnotatedElementUtils.getMergedRepeatableAnnotations(
								method, Scheduled.class, Schedules.class);
						return (!scheduledAnnotations.isEmpty() ? scheduledAnnotations : null);
					});
			
			final boolean isScheduled = !annotatedMethods.isEmpty();

			final BatchMetadata metadata = new BatchMetadata();
			metadata.setBatchId(batchId);
			metadata.setBatchBeanName(batchBeanName);
			metadata.setScheduled(isScheduled);

			map.put(batchId, metadata);
		}

		log.info("batchMapping={}", map);
		batchMapping.addAll(map);
	}

}
