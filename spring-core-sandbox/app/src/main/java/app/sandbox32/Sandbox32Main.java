package app.sandbox32;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox32Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox32Config.class);
		Sandbox32Hoge hoge = context.getBean(Sandbox32Hoge.class);
		hoge.hoge();

	}
}
