package epsilongtmyon.sandbox01.app.common.exit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 終了コードを解決するクラス
 */
@Component
@Order(Ordered.LOWEST_PRECEDENCE) // 優先順位をつけることができる(これは一番最後にする例)
public class MyExitCodeResolver implements ExitCodeGenerator, ExitCodeExceptionMapper {
	
	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(MyExitCodeResolver.class);

	private int exitCode;

	// -----------------
	// ExitCodeGenerator
	// SpringApplication.exitを実行したときに取得される

	@Override
	public int getExitCode() {
		return exitCode;
	}

	public void setExitCode(int exitCode) {
		logger.info("setExitCode {}", exitCode);
		this.exitCode = exitCode;
	}

	// -----------------
	// ExitCodeExceptionMapper
	// ApplicationRunnerなどで例外がスローされたときに呼ばれる。
	// ここで返したコードは UncaughtExceptionHandler で利用されるので
	// スレッドの途中で例外を握りつぶしてはいけない

	@Override
	public int getExitCode(Throwable exception) {
		// ここで0以外の値を返すと、その値が終了コードとして後で利用されるようになる。
		
		// 異常時は9固定にする。
		return 9;
	}

}
