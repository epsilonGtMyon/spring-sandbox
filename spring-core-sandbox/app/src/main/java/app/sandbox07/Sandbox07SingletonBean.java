package app.sandbox07;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Sandbox07SingletonBean implements InitializingBean, DisposableBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		System.err.println(getClass() + ":afterPropertiesSet");
		Thread.dumpStack();
	}

	@PostConstruct
	public void postConstruct() {
		System.err.println(getClass() + ":postConstruct");
		Thread.dumpStack();
	}

	@Override
	public void destroy() throws Exception {
		System.err.println(getClass() + ":destroy");
		Thread.dumpStack();
	}

	@PreDestroy
	public void preDestroy() {
		System.err.println(getClass() + ":preDestroy");
		Thread.dumpStack();
	}

}
