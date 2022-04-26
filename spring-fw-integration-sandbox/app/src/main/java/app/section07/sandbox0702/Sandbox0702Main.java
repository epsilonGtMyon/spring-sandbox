package app.section07.sandbox0702;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0702Main {


	public static void main(String[] args) throws IOException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox0702Config.class)) {

			Sandbox0702TaskExecutorSample sample = context.getBean(Sandbox0702TaskExecutorSample.class);
			sample.printMessages();

			System.out.println("wait..");
			System.in.read();
		}
	}
}
