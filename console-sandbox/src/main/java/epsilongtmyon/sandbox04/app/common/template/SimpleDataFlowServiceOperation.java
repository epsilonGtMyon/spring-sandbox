package epsilongtmyon.sandbox04.app.common.template;

import java.util.List;
import java.util.stream.Stream;

/**
 * {@link SimpleDataFlowServiceTemplate}で使われる操作
 * 
 * @param <P> パラメータの型
 * @param <R> 読み取ったデータの型
 * @param <W> 書き込みに使うデータの型
 */
public interface SimpleDataFlowServiceOperation<P, R, W> {

	/**
	 * 処理のチャンクサイズを返します。
	 * @return チャンクサイズ
	 */
	public default int getChunkSize() {
		return 11;
	}

	/**
	 * データを読み取って{@link Stream}として返します。
	 * 
	 * @param param パラメータ
	 * @return 読み取りデータのStream
	 */
	Stream<R> readAsStream(P param);

	/**
	 * 読み取ったデータを書き込み対象の型に変換します。
	 * @param readItem 読み取ったデータ
	 * @param param パラメータの型
	 * @return 書き込み対象の型
	 */
	W process(R readItem, P param);

	/**
	 * 書き込みを行います。
	 * @param writeItems 書き込み対象のデータ
	 * @param param パラメータ
	 */
	void writeChunk(List<W> writeItems, P param);
}
