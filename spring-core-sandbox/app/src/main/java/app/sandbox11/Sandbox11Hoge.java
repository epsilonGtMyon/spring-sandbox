package app.sandbox11;

public class Sandbox11Hoge {

	private final Sandbox11Foo sandbox11Foo;

	public Sandbox11Hoge(Sandbox11Foo sandbox11Foo) {
		Thread.dumpStack();
		this.sandbox11Foo = sandbox11Foo;
	}

	public void execute() {
		sandbox11Foo.hello();
	}

}
