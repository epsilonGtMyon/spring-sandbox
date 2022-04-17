package app.sandbox29;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox29Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox29Config.class)) {
		}
	}
}
