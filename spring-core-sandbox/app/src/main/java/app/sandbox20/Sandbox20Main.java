package app.sandbox20;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import app.sandbox20.primary.Sandbox20Hoge;
import app.sandbox20.primary.Sandbox20PrimaryConfig;

public class Sandbox20Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox20PrimaryConfig.class)) {
			Sandbox20Hoge hoge = context.getBean(Sandbox20Hoge.class);
			hoge.execute();
		}
	}
}
