package app.section02.sandbox0202;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0202Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox0202Config.class)) {
		}
	}
}
