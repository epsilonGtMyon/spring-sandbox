package app.sandbox11;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationMain {

	public static void main(String[] args) {
		AnnotationMain main = new AnnotationMain();
		main.start();

	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app/sandbox11/application.xml")) {

			Sandbox11Hoge hoge = context.getBean(Sandbox11Hoge.class);
			Sandbox11Hoge2 hoge2 = context.getBean(Sandbox11Hoge2.class);

			hoge.execute();
			hoge2.execute();
		}
	}
}
