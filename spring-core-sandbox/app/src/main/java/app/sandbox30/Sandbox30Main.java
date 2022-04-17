package app.sandbox30;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox30Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox30Config.class)) {

			Sandbox30Hoge hoge = context.getBean(Sandbox30Hoge.class);
			hoge.hello();
		}
	}
}
