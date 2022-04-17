package app.sandbox22;

import java.util.Map;

import org.springframework.core.env.PropertySource;

//自前のPropertySource
public class Sandbox22PropertySource extends PropertySource<String> {

	private final Map<String, String> map = Map.of(
			"key1", "value1",
			"key2", "value2",
			"key3", "value3",
			"key4", "value4");

	public Sandbox22PropertySource() {
		super("Sandbox22PropertySource");
	}

	@Override
	public Object getProperty(String name) {
		return map.get(name);
	}

}
