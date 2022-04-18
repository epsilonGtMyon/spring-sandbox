package app.section04.sandbox0403;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0403Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox0403Config.class)) {
			Sandbox0403Hoge hoge = context.getBean(Sandbox0403Hoge.class);
			hoge.execute();
		}
	}
}
