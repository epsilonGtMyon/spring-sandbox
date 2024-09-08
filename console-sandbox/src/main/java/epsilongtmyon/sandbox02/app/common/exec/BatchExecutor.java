package epsilongtmyon.sandbox02.app.common.exec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

@Component
public class BatchExecutor {

	private final BatchMapping batchMapping;

	public BatchExecutor(BatchMapping batchMapping) {
		super();
		this.batchMapping = batchMapping;
	}

	public Object execute() {
		// TODO 動的に取得したい
		String batchId = "B001";

		final Object batchEntry = batchMapping.getBatchEntry(batchId);

		try {
			Class<?> batchClass = batchEntry.getClass();

			// TODO Webのように実行するメソッドを動的に決定して、引数も動的に解決したい
			Method method = batchClass.getDeclaredMethod("execute");
			Object result = method.invoke(batchEntry);
			return result;
		} catch (InvocationTargetException ex) {
			Throwable cause = ex.getCause();
			throw new RuntimeException(cause);
		} catch (ReflectiveOperationException ex) {
			throw new RuntimeException(ex);
		}

	}
}
