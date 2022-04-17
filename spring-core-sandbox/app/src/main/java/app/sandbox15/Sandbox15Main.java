package app.sandbox15;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = Sandbox15Main.class)
public class Sandbox15Main {

	public static void main(String[] args) {
		Sandbox15Main main  = new Sandbox15Main();

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox15Main.class)) {
			main.start1(context);

			main.start2(context);



		}
	}


	private void start1(AnnotationConfigApplicationContext context) {
		Sandbox15Hoge hoge = context.getBean(Sandbox15Hoge.class);
	}

	private void start2(AnnotationConfigApplicationContext context) {
		Sandbox15Hoge hoge = context.getBean(Sandbox15Hoge.class);
	}


}
