package app.sandbox01;

public class Hoge2 {

	public final Foo foo;

	public final Bar bar;

	public Hoge2(Foo foo, Bar bar) {
		super();
		this.foo = foo;
		this.bar = bar;
	}

	@Override
	public String toString() {
		return "Hoge2 [foo=" + foo + ", bar=" + bar + "]";
	}

}
