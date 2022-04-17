package app.sandbox20.primary;

import app.sandbox20.sub.Sandbox20Foo;

public class Sandbox20Hoge {

	private final Sandbox20Foo sandbox20Foo;

	public Sandbox20Hoge(Sandbox20Foo sandbox20Foo) {
		this.sandbox20Foo = sandbox20Foo;
	}

	public void execute() {
		System.out.println("hello:" + sandbox20Foo.getName());
	}
}
