package app.section03.sandbox0303;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0303Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox0303Config.class)) {

			Sandbox0303Service service = context.getBean(Sandbox0303Service.class);
			service.execute();
		}
	}
}
