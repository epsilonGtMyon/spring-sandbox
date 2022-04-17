package app.sandbox18;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox18Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox18Config.class)) {

			Sandbox18Hoge hoge = context.getBean(Sandbox18Hoge.class);
			hoge.execute();
		}
	}
}
