package app.sandbox06;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyMain {

	public static void main(String[] args) {
		final ProxyMain main = new ProxyMain();
		main.start1();

	}

	private void start1() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app/sandbox06/application.xml")) {
//			context.getBeanFactory().registerScope(null, null);
			Sandbox06Singleton bean = context.getBean(Sandbox06Singleton.class);

			bean.execute();


		}
	}
}
