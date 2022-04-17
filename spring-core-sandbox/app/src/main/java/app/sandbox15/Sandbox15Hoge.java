package app.sandbox15;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Sandbox15Hoge {

	public final Sandbox15Bean sandbox15Bean;

	public Sandbox15Hoge(Sandbox15Bean sandbox15Bean) {
		super();
		this.sandbox15Bean = sandbox15Bean;
	}

}
