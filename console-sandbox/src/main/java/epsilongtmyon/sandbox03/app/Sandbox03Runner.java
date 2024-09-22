package epsilongtmyon.sandbox03.app;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox03.app.common.param.CommandLineArgsBinder;

@Component
public class Sandbox03Runner implements ApplicationRunner {

	private CommandLineArgsBinder binder;

	public Sandbox03Runner(CommandLineArgsBinder binder) {
		super();
		this.binder = binder;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		// バインドしてコマンドラインで確認
		Sandbox03Param param = binder.bindAndGetAs(Sandbox03Param.class);
		System.out.println(param);
	}

}
