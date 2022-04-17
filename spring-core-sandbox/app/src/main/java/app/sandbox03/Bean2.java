package app.sandbox03;

import java.io.Closeable;
import java.io.IOException;

public class Bean2 implements Closeable  {

	public final String name;

	public Bean2(String name) {
		this.name = name;
		System.out.println("ctor Bean2:" + name);
	}

	@Override
	public void close() throws IOException {
		System.out.println("bean2:close");
	}

}
