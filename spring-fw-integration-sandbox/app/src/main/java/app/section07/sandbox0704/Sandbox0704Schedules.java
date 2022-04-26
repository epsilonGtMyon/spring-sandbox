package app.section07.sandbox0704;

import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Sandbox0704Schedules {

	// Componentのメソッドにもつけることができる
	// その場合 このメソッドが定期的に実行される。
	//
	// メソッドは戻り値がvoidで引数を受け付けてはならない

	//timeUnitを使うこともできる。
	@Scheduled(fixedDelay = 5, timeUnit = TimeUnit.SECONDS)
	public void execute01() {
		System.out.println("execute01");
	}

	//fixedRateを使うこともできる。
	@Scheduled(fixedRate = 5, timeUnit = TimeUnit.SECONDS)
	public void execute02() {
		System.out.println("execute02");
	}

	//initialDelay を使うこともできる。
	@Scheduled(initialDelay = 2, fixedRate = 5, timeUnit = TimeUnit.SECONDS)
	public void execute03() {
		System.out.println("execute03");
	}

	//cronの記法を使うこともできる。(zone もね)
	@Scheduled(cron="*/5 * * * * MON-FRI")
	public void execute04() {
		System.out.println("execute04");
	}
}
