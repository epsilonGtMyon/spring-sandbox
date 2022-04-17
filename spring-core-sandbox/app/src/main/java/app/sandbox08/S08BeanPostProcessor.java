package app.sandbox08;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class S08BeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.err.println();
		System.err.println("postProcessBeforeInitialization");
		System.err.println(bean);
		System.err.println(beanName);

		Thread.dumpStack();
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.err.println();
		System.err.println("postProcessAfterInitialization");
		System.err.println(bean);
		System.err.println(beanName);

		Thread.dumpStack();
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

}
