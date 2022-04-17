package app.sandbox17;

import org.springframework.stereotype.Component;

@Component
public class Sandbox17Hoge {

	private final Sandbox17 sandbox17;

	public Sandbox17Hoge(Sandbox17 sandbox17) {
		this.sandbox17 = sandbox17;
	}

	public void execute() {
		System.out.println(sandbox17.hello());
	}
}
