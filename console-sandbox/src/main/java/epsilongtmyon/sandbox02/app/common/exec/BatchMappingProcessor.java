package epsilongtmyon.sandbox02.app.common.exec;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * バッチのIDとBean名のマッピングを作成するクラス
 */
@Component
public class BatchMappingProcessor implements BeanPostProcessor, BeanFactoryAware {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(BatchMappingProcessor.class);

	private ListableBeanFactory beanFactory;

	public BatchMappingProcessor() {
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof BatchMapping batchMapping) {
			final Map<String, BatchMetadata> map = BatchMapping.loadMapping(beanFactory);

			log.info("batchMapping={}", map);
			batchMapping.addAll(map);
		}
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (ListableBeanFactory) beanFactory;
	}

}
