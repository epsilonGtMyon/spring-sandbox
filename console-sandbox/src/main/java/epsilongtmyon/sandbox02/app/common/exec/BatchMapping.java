package epsilongtmyon.sandbox02.app.common.exec;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.annotation.Batch;

/**
 * バッチのIDとBean名のマッピングを保持するクラス
 */
@Component
public class BatchMapping {

	/** バッチのIDとメタデータのマッピング */
	private final ConcurrentHashMap<String, BatchMetadata> mapping = new ConcurrentHashMap<>();

	/**
	 * マッピングを追加します。
	 * @param mp マッピング
	 */
	public void addAll(Map<String, BatchMetadata> mp) {
		mapping.putAll(mp);
	}

	/**
	 * バッチのIDからメタデータを取得します。
	 * @param batchId バッチのID
	 * @return メタデータ
	 */
	public BatchMetadata getMetadata(String batchId) {
		return mapping.get(batchId);
	}

	public static Map<String, BatchMetadata> loadMapping(ListableBeanFactory beanFactory) {
		final String[] batchBeanNames = beanFactory.getBeanNamesForAnnotation(Batch.class);
		final Map<String, BatchMetadata> map = new HashMap<>(batchBeanNames.length);

		for (String batchBeanName : batchBeanNames) {

			final Batch annotation = beanFactory.findAnnotationOnBean(batchBeanName, Batch.class);
			final String batchId = annotation.value();

			if (map.containsKey(batchId)) {
				throw new IllegalStateException("batchId[" + batchId + "] is dupprecated.");
			}

			Class<?> clazz = beanFactory.getType(batchBeanName);
			// 参考:ScheduledAnnotationBeanPostProcessor#postProcessAfterInitialization
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
		
		return map;
	}
}
