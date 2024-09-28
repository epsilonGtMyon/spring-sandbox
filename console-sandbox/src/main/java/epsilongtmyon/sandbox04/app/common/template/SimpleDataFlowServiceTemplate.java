package epsilongtmyon.sandbox04.app.common.template;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 単純なデータ移動のテンプレート
 * 
 * @param <P> パラメータの型
 * @param <R> 読み取ったデータの型
 * @param <W> 書き込みに使うデータの型
 */
public abstract class SimpleDataFlowServiceTemplate<P, R, W> {

	/** テンプレートのオペレーション部分 */
	private SimpleDataFlowServiceOperation<P, R, W> operation;

	public SimpleDataFlowServiceTemplate(SimpleDataFlowServiceOperation<P, R, W> operation) {
		this.operation = operation;
	}

	/**
	 * テンプレート処理を実行します。
	 * @param param
	 */
	public void executeTemplate(P param) {

		final int chunkSize = operation.getChunkSize();
		try (Stream<R> stream = operation.readAsStream(param)) {
			Stream<W> stream2 = stream
					.map(r -> operation.process(r, param));

			Iterator<W> it = stream2.iterator();
			List<W> writeItems = new ArrayList<>();
			while (it.hasNext()) {
				W writeItem = it.next();
				writeItems.add(writeItem);

				if (writeItems.size() >= chunkSize) {
					operation.writeChunk(writeItems, param);
					writeItems.clear();
				}
			}

			if (!writeItems.isEmpty()) {
				operation.writeChunk(writeItems, param);
				writeItems.clear();
			}

		}

	}

}
