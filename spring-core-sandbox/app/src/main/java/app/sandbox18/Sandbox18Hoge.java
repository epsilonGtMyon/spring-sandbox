package app.sandbox18;

public class Sandbox18Hoge {

	private final Sandbox18Foo sandbox18Foo;

	public Sandbox18Hoge(Sandbox18Foo sandbox18Foo) {
		this.sandbox18Foo = sandbox18Foo;
	}

	public void execute() {
		System.out.println("hello:" + sandbox18Foo.getName());
	}
}
