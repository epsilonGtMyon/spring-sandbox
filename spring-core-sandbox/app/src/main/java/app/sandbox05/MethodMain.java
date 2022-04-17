package app.sandbox05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MethodMain {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox05/application.xml")) {

			MethodMain main = new MethodMain();
			//main.start1(context);
			main.start2(context);
		}
	}

	private void start1(ApplicationContext context) {

		System.out.println("-------------");
		System.out.println(context.getBean(Sandbox05Prototype.class));
		System.out.println(context.getBean(Sandbox05Prototype.class));
		System.out.println("-------------");
		System.out.println(context.getBean(Sandbox05Singleton.class));
		System.out.println(context.getBean(Sandbox05Singleton.class));
		System.out.println("-------------");

		Sandbox05Singleton bean = context.getBean(Sandbox05Singleton.class);
		System.out.println(bean);
		bean.execute();
		bean.execute();
	}

	private void start2(ApplicationContext context) {
		System.out.println(context.getBean(Sandbox05Singleton.class));
		System.out.println(context.getBean(Sandbox05Prototype.class));
	}
}
