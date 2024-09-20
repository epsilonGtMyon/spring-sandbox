package epsilongtmyon.sandbox02.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.BatchExecutor;
import epsilongtmyon.sandbox02.app.common.exec.annotation.BatchRunner;

@BatchRunner
@Component
public class Sandbox02Runner implements ApplicationRunner {

	private final BatchExecutor executor;

	public Sandbox02Runner(BatchExecutor executor) {
		super();
		this.executor = executor;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Scheduledのバッチならexecuteを実行することなく即returnしたい
		// @BatchRunner というConditonalなアノテーション作って 実行対象のバッチがScheduleならfalseにするとか？？
		
		executor.execute();
	}

}
