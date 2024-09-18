package epsilongtmyon.sandbox02.app.common.exec;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 * バッチのIDとBean名のマッピングを保持するクラス
 */
@Component
public class BatchMapping {

	/** バッチのIDとBean名のマッピング */
	private final ConcurrentHashMap<String, String> mapping = new ConcurrentHashMap<>();

	/**
	 * マッピングを追加します。
	 * @param mp マッピング
	 */
	public void addAll(Map<String, String> mp) {
		mapping.putAll(mp);
	}

	/**
	 * バッチのIDからBean名を取得します。
	 * @param batchId バッチのID
	 * @return Beanの名前
	 */
	public String getBatchEntryBeanName(String batchId) {
		return mapping.get(batchId);
	}
}
