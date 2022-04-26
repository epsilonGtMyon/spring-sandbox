package app.section07.sandbox0703;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

public class Sandbox0703Main {

	public static void main(String[] args) throws Exception {
		ScheduledFuture<?> future = ex04();

		System.out.println("wait...");
		System.in.read();
		future.cancel(true);
		System.out.println("finish");
	}

	//ThreadPoolTaskSchedulerだと ScheduledExecutorServiceの初期化が必要

	private static ScheduledFuture<?> ex01() throws InterruptedException, ExecutionException {
		TaskScheduler scheduler = new ConcurrentTaskScheduler();
		Instant startTime = Instant.now().plusSeconds(5);
		System.out.println(startTime);

		ScheduledFuture<?> future = scheduler.schedule(() -> {
			System.out.println("running");
		}, startTime);

		return future;
	}

	//fixedRateだと開始時刻から指定時間経過後に次のタスクを実行する。
	private static ScheduledFuture<?> ex02() {
		ConcurrentTaskScheduler scheduler = new ConcurrentTaskScheduler();

		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {
			System.out.println(LocalDateTime.now());
			try {
				System.out.println("start!");
				TimeUnit.SECONDS.sleep(2);
				System.out.println("end!");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, Duration.ofSeconds(3));

		return future;

	}

	//delayだと 完了後に指定時間経過後に次のタスクを実行する。(つまりこの例だと5秒間隔)
	private static ScheduledFuture<?> ex03() {
		ConcurrentTaskScheduler scheduler = new ConcurrentTaskScheduler();

		ScheduledFuture<?> future = scheduler.scheduleWithFixedDelay(() -> {
			System.out.println(LocalDateTime.now());
			try {
				System.out.println("start!");
				TimeUnit.SECONDS.sleep(2);
				System.out.println("end!");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, Duration.ofSeconds(3));

		return future;
	}

	//scheduleメソッドはTriggerを受け取ることができる
	//このインターフェースは次回実行のDateを返すという単純でかつ動的な実行スケジューリングができる。
	//実装の一つとしてcron形式で指定できるものがある。
	private static ScheduledFuture<?> ex04() {
		ConcurrentTaskScheduler scheduler = new ConcurrentTaskScheduler();

		ScheduledFuture<?> future = scheduler.schedule(() -> {
			System.out.println(LocalDateTime.now());
			try {
				System.out.println("start!");
				TimeUnit.SECONDS.sleep(2);
				System.out.println("end!");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}, new CronTrigger("0/5 * * * * *"));

		return future;
	}
}
