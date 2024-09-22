package epsilongtmyon.sandbox02.app.common.exec;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

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

		final Map<String, BatchMetadata> map = BatchMapping.loadMapping(context);

		log.info("batchMapping={}", map);
		batchMapping.addAll(map);
	}

}
