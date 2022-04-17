package app.sandbox31;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox31Main {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Sandbox31Config.class);
		Sandbox31Hoge hoge = context.getBean(Sandbox31Hoge.class);
		hoge.hoge();

	}
}
