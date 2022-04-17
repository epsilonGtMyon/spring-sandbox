package app.sandbox01;

public class Hoge {

	private Foo foo;

	private Bar bar;

	public Hoge() {
	}

	public Foo getFoo() {
		return foo;
	}

	public void setFoo(Foo foo) {
		this.foo = foo;
	}

	public Bar getBar() {
		return bar;
	}

	public void setBar(Bar bar) {
		Thread.dumpStack();
		this.bar = bar;
	}

	@Override
	public String toString() {
		return "Hoge [foo=" + foo + ", bar=" + bar + "]";
	}

}
