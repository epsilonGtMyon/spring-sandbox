package app.sandbox02;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox02/application.xml")) {

			Sandbox02 bean = context.getBean(Sandbox02.class);
			System.out.println(bean);

			Prop2 bean2 = context.getBean(Prop2.class);
			System.out.println(bean2);
		}
	}
}
