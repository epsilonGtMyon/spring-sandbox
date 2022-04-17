package app.sandbox25;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox25Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox25Config.class)) {
			context.publishEvent(new Sandbox25Event("yahoo1"));
			context.publishEvent(new Sandbox25Event("yahoo2"));
		}
	}
}
