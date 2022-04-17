package app.sandbox03;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DependsOnMain {

	public static void main(String[] args) {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"app/sandbox03/application.xml")) {

			context.getBean(Bean3.class);
		}
	}
}
