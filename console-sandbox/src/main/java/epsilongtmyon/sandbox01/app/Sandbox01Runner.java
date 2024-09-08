package epsilongtmyon.sandbox01.app;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox01.app.common.exit.MyExitCodeResolver;

/*
 * ApplicationRunnerと似たものにCommandLineRunnerというものがあるが、そちらよりもこちらの方が引数の取り扱いが便利になっている。
 */
@Component
public class Sandbox01Runner implements ApplicationRunner {

	/** ロガー */
	private static final Logger logger = LoggerFactory.getLogger(Sandbox01Runner.class);

	private final Sandbox01Properties props;

	private final MyExitCodeResolver exitCodeResolver;

	public Sandbox01Runner(Sandbox01Properties props, MyExitCodeResolver exitCodeResolver) {
		super();
		this.props = props;
		this.exitCodeResolver = exitCodeResolver;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		//-------------------------------
		// ConfigurationProperties にコマンドラインの内容が反映されている。
		logger.info("print Sandbox01Properties {}", props);

		//-------------------------------
		// ApplicationArguments の確認
		// 実装クラスはDefaultApplicationArguments
		// 変換の仕組みはSimpleCommandLineArgsParserを参照

		// getSourceArgs は渡された生の値が入っている。
		String[] sourceArgs = args.getSourceArgs();
		logger.info("print sourceArgs {}", Arrays.toString(sourceArgs));

		// getNonOptionArgs は「--」から始まらないものの一覧
		List<String> nonOptionArgs = args.getNonOptionArgs();
		logger.info("print nonOptionArgs {}", nonOptionArgs);

		// getOptionNamesは「--」から始まっているものの一覧
		Set<String> optionNames = args.getOptionNames();
		logger.info("print optionNames {}", optionNames);
		optionNames.forEach(name -> {
			// getOptionValues でキーに対する値を取得できる。
			logger.info("print key:{}, value:{}", name, args.getOptionValues(name));
		});

		// -------------------------------
		// 例外を投げて終了させる。
		if (false) {
			throw new Exception("hello");
		}

		//-------------------------------
		// 終了コード
		exitCodeResolver.setExitCode(2);
	}

}
