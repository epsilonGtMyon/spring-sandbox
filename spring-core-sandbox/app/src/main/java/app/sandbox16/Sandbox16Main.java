package app.sandbox16;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ConfigurationだとCGLIBのプロキシが使われてBeanメソッドが呼び出される
@Configuration
//@Component
public class Sandbox16Main {

	public static void main(String[] args) {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox16Main.class)) {

		}
	}

	@Bean
	public Sandbox16Main exSandbox16Main() {
		Thread.dumpStack();
		return new Sandbox16Main();
	}
}
