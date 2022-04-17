package app.sandbox14;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "app.sandbox14")
public class Sandbox14Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox14Main.class)) {

			Sandbox14Hoge hoge = context.getBean(Sandbox14Hoge.class);
			hoge.execute();
		}

	}
}
