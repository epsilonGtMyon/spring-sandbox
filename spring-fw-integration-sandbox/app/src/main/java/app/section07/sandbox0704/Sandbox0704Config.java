package app.section07.sandbox0704;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

@Configuration
@EnableScheduling //@Scheduled が使えるように
@EnableAsync //@Async が使えるように
//SchedulingConfigurer , AsyncConfigurer を実装すると細かい調整ができる。
public class Sandbox0704Config {

	// Bean登録していない場合は デフォルトのモノが使われる
	// ScheduledTaskRegistrar#scheduleTasks メソッドを参照
	// デフォルトだと Executors.newSingleThreadScheduledExecutorが使われるようなので
	// 多分、長時間のタスクを複数入れるとつっかえる気がする。
	@Bean
	public ConcurrentTaskScheduler taskScheduler() {
		return new ConcurrentTaskScheduler();
	}

	// ScheduledTaskRegistrar で登録される
	// 登録の時点でTasuSchedulerでscheduleXxxxが呼ばれて実行されている。
	@Scheduled(fixedDelay = 5000)
	public void doSomething() {
		System.out.println("doSomething");
	}

	@Bean
	public Sandbox0704Schedules sandbox0704Schedules() {
		return new Sandbox0704Schedules();
	}
}
