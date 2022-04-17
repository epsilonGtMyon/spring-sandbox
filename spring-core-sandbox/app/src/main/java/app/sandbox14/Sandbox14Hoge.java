package app.sandbox14;

import org.springframework.stereotype.Component;

@Component
public class Sandbox14Hoge {

	private final Sandbox14Foo sandbox14Foo;

	public Sandbox14Hoge(Sandbox14Foo sandbox14Foo) {
		this.sandbox14Foo = sandbox14Foo;
	}

	public void execute() {
		System.out.println(sandbox14Foo.hello());
	}
}
