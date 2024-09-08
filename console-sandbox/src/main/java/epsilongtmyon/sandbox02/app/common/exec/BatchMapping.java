package epsilongtmyon.sandbox02.app.common.exec;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class BatchMapping {

	private final ConcurrentHashMap<String, Object> mapping = new ConcurrentHashMap<>();

	public void addAll(Map<String, Object> mp) {
		mapping.putAll(mp);
	}

	public Object getBatchEntry(String batchId) {
		return mapping.get(batchId);
	}
}
