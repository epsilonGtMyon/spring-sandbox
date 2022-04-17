package app.sandbox26;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox26Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox26Config.class)) {
			context.publishEvent(new Sandbox26Event("yahoo1!"));
		}
	}
}
