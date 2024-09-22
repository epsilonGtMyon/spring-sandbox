package epsilongtmyon.sandbox02.app.common.exec;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.annotation.BatchRunner;

@Component
public class BatchRunnerDefinitionRemoveProcessor implements BeanDefinitionRegistryPostProcessor {
	
	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(BatchRunnerDefinitionRemoveProcessor.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		final ConfigurableListableBeanFactory beanFactory = (ConfigurableListableBeanFactory) registry;

		final Map<String, BatchMetadata> map = BatchMapping.loadMapping(beanFactory);

		boolean hasScheduled = map.values().stream().anyMatch(x -> x.isScheduled());
		if (!hasScheduled) {
			log.info("BatchRunner is not removed.");
			return;
		}

		// ある時はBatchRunnerは動いてほしくないのでBean定義を削除
		String[] batchRunnerBeanNames = beanFactory.getBeanNamesForAnnotation(BatchRunner.class);
		for (String batchRunnerBeanName : batchRunnerBeanNames) {
			registry.removeBeanDefinition(batchRunnerBeanName);
		}
		log.info("BatchRunner is removed.");
	}

}
