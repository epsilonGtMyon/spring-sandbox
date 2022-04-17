package app.sandbox03;

import java.io.Closeable;
import java.io.IOException;

public class Bean3 implements Closeable  {

	public final String name;

	public Bean3(String name) {
		this.name = name;
		System.out.println("ctor Bean3:" + name);
		Thread.dumpStack();
	}

	@Override
	public void close() throws IOException {
		System.out.println("bean3:close");
	}

}
