package app.sandbox17;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Sandbox17Foo implements Sandbox17 {

	@Override
	public String hello() {
		return "hello:" + this.toString();
	}

}
