package app.section03.sandbox0301;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0301Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox0301Config.class);
		Sandbox0301Hoge hoge = context.getBean(Sandbox0301Hoge.class);
		hoge.hoge();

	}
}
