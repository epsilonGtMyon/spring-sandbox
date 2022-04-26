package app.section07.sandbox0705;

import java.io.IOException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Sandbox0705Main {

	public static void main(String[] args) throws IOException {

		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox0705Config.class)) {

			Sandbox0705Hoge bean = context.getBean(Sandbox0705Hoge.class);

			//非同期呼び出しになる。
			bean.doAsync(10);

			System.out.println("wait...");
			System.in.read();
		}
	}
}
