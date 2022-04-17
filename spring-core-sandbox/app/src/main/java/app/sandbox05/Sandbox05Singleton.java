package app.sandbox05;

//具象クラスでメソッドがreturn nullでもよいよ

public abstract class Sandbox05Singleton {

	public Sandbox05Singleton() {
		System.out.println("ctor Sandbox05Singleton");
	}

	public void execute() {
		System.out.println(gen());
	}

	protected abstract Sandbox05Prototype gen();

}
