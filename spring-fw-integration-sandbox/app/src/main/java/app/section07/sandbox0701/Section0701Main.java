package app.section07.sandbox0701;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

public class Section0701Main {

	public static void main(String[] args) throws IOException {
		ex02();

		System.out.println("wait...");
		System.in.read();
		System.out.println("end!!");
	}

	/*
	 * SyncTaskExecutorは同期実行
	 */
	private static void ex01() {
		printThreadId();

		TaskExecutor executor = new SyncTaskExecutor();
		executor.execute(() -> {
			System.out.println("01-start");
			printThreadId();
			sleep(1);
			System.out.println("01-end");
		});
		executor.execute(() -> {
			printThreadId();
			System.out.println("02-start");
			sleep(1);
			System.out.println("02-end");
		});

	}

	/*
	 * スレッドを毎回作る
	 * setConcurrencyLimitで同時実行数の制限がつけれるみたい
	 * その時はexecuteがブロックされている
	 */
	private static void ex02() {
		printThreadId();

		SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
		executor.setConcurrencyLimit(1);
		executor.execute(() -> {
			printThreadId();
			System.out.println("01-start");
			sleep(5);
			System.out.println("01-end");
		});
		//↓制限に到達するとこれがブロックされる
		executor.execute(() -> {
			printThreadId();
			System.out.println("02-start");
			sleep(5);
			System.out.println("02-end");
		});
	}

	// 他に
	//ConcurrentTaskExecutor
	//ThreadPoolTaskExecutor ← 一般的らしい
	//WorkManagerTaskExecutor ← CommonJ 連携
	//DefaultManagedTaskExecutor ← ExecutorとしてManagedExecutorService を使う(ConcurrentTaskExecutorを継承)

	private static void printThreadId() {
		long threadId = Thread.currentThread().getId();
		System.out.println("ThreadId:" + threadId);
	}

	private static void sleep(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
