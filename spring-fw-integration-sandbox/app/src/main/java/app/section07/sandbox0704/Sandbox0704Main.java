package app.section07.sandbox0704;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0704Main {


	public static void main(String[] args) throws IOException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox0704Config.class)) {

			System.in.read();
		}
	}
}
