package app.sandbox07;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifeCycleMain {

	public static void main(String[] args) {
		LifeCycleMain main = new LifeCycleMain();
		main.start1();
	}

	private void start1() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox07/application.xml")) {
			context.getBean(Sandbox07SingletonBean.class);
			System.err.println("-----------");
			context.getBean(Sandbox07Singleton2Bean.class);
			System.err.println("-----------");
			context.getBean(Sandbox07PrototypeBean.class);

		}
	}
}
