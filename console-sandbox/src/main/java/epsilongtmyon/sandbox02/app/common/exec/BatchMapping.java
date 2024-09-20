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

}
