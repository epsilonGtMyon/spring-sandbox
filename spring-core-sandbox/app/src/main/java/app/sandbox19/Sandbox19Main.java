package app.sandbox19;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox19Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox19Config.class)) {

			Sandbox19Hoge hoge = context.getBean(Sandbox19Hoge.class);
			hoge.execute();
		}
	}
}
