package app.sandbox13;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QualifierMain {

	public static void main(String[] args) {
		QualifierMain main = new QualifierMain();
		main.start();
	}

	private void start() {

		try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app/sandbox13/application.xml")) {

			Sandbox13Hoge hoge = context.getBean(Sandbox13Hoge.class);
			Sandbox13Hoge2 hoge2 = context.getBean(Sandbox13Hoge2.class);

			hoge.execute();
			hoge2.execute();
		}
	}
}
