package epsilongtmyon.sandbox02.app.common.exec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class BatchExecutor implements BeanFactoryAware {

	private final ApplicationArguments applicationArguments;

	private final BatchMapping batchMapping;

	private BeanFactory beanFactory;

	public BatchExecutor(ApplicationArguments applicationArguments, BatchMapping batchMapping) {
		super();
		this.applicationArguments = applicationArguments;
		this.batchMapping = batchMapping;
	}

	public Object execute() {
		final String batchId = applicationArguments.getOptionValues("batchId").get(0);

		final String batchEntryBeanName = batchMapping.getBatchEntryBeanName(batchId);
		final Object batchEntry = beanFactory.getBean(batchEntryBeanName);

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

	// ------
	// BeanFactoryAware

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;
	}
}
