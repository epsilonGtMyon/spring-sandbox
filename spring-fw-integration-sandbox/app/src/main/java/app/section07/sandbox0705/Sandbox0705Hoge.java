package app.section07.sandbox0705;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Sandbox0705Hoge {

	@Async
	public void doAsync(int x) {

		try {
			System.out.println("doAsync:begin");
			TimeUnit.SECONDS.sleep(2);
			System.out.println("doAsync:end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
