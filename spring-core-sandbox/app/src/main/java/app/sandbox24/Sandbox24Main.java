package app.sandbox24;

import java.util.Locale;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox24Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox24Config.class);

		String message = context.getMessage("A001", new Object[] { "xxx" }, null, Locale.getDefault());
		System.out.println(message);
	}
}
