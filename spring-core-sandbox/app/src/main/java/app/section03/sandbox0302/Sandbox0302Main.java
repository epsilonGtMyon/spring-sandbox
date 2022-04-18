package app.section03.sandbox0302;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0302Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox0302Config.class);
		Sandbox0302Hoge hoge = context.getBean(Sandbox0302Hoge.class);
		hoge.hoge();

	}
}
