package app.sandbox04;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowireMain {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox04/application.xml")) {

			Sandbox04Controller bean = context.getBean(Sandbox04Controller.class);
			bean.execute();
		}
	}
}
