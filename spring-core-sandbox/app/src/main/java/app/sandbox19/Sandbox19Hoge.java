package app.sandbox19;

public class Sandbox19Hoge {

	private final Sandbox19Foo sandbox19Foo;

	public Sandbox19Hoge(Sandbox19Foo sandbox19Foo) {
		this.sandbox19Foo = sandbox19Foo;
	}

	public void execute() {
		System.out.println("hello:" + sandbox19Foo.getName());
		System.out.println("hello:" + sandbox19Foo.getName());
	}
}
