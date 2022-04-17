package app.sandbox33;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox33Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox33Config.class)) {

			Sandbox33Service service = context.getBean(Sandbox33Service.class);
			service.execute();
		}
	}
}
