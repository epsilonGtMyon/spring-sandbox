package app.sandbox07;

public class Sandbox07Singleton2Bean {

	public void init() {
		System.err.println(getClass() + ":init");
		Thread.dumpStack();
	}

	public void close() {
		System.err.println(getClass() + ":close");
		Thread.dumpStack();
	}

}
