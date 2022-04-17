package app.sandbox03;

import java.io.Closeable;
import java.io.IOException;

public class Bean1 implements Closeable {

	public final String name;

	public Bean1(String name) {
		this.name = name;
		System.out.println("ctor Bean1:" + name);
	}

	@Override
	public void close() throws IOException {
		System.out.println("bean1:close");
	}

}
