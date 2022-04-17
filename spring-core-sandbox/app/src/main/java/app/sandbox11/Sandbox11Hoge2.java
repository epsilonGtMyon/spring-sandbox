package app.sandbox11;

import org.springframework.beans.factory.annotation.Autowired;

public class Sandbox11Hoge2 {

	private Sandbox11Foo sandbox11Foo;

	@Autowired
	public void setSandbox11Foo(Sandbox11Foo sandbox11Foo) {
		Thread.dumpStack();
		this.sandbox11Foo = sandbox11Foo;
	}

	public void execute() {
		sandbox11Foo.hello();
	}

}
