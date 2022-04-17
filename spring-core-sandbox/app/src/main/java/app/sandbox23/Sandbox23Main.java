package app.sandbox23;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class Sandbox23Main {


	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox23Config.class);
		ConfigurableEnvironment env = context.getEnvironment();

		String aaa = env.getProperty("app.sandbox23.aaa");
		System.out.println(aaa);

	}
}
