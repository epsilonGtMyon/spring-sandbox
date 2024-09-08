package epsilongtmyon.sandbox01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import epsilongtmyon.sandbox01.app.Sandbox01Properties;

@SpringBootApplication
@EnableConfigurationProperties(Sandbox01Properties.class)
public class Sandbox01Main {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Sandbox01Main.class);

	public static void main(String[] args) {
		args = new String[] { "--a01=1", "--a01=2,3", "--a02=1", "/hoge", "--sandbox01.value01=aaa" };

		logger.info("start");
		int exitCode = -1;
		try (ConfigurableApplicationContext context = SpringApplication.run(Sandbox01Main.class, args)) {
			exitCode = SpringApplication.exit(context);
		} catch (Throwable t) {
			// cf) SpringApplication#handleRunFailure
			// ここの例外はRuntimeExceptionならそのままだがそれ以外ならIllegalStateExceptionでラップされている。

			logger.error("error occured", t);
			// throw しないとExitCodeExceptionMapperが動かないので握りつぶしてはいけない
			throw t;
		}

		// 例外が発生していない場合の終了処理
		final Level level = exitCode > 0 ? Level.ERROR : Level.INFO;
		logger.atLevel(level).log("finish with exitCode {}", exitCode);
		System.exit(exitCode);
	}

}
