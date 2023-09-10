package epsilongtmyon.app.endpoint.sandbox01;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Sandbox01Session implements Serializable {

	private String value01;

	private String value02;

	public String getValue01() {
		return value01;
	}

	public void setValue01(String value01) {
		this.value01 = value01;
	}

	public String getValue02() {
		return value02;
	}

	public void setValue02(String value02) {
		this.value02 = value02;
	}

	@Override
	public String toString() {
		return "Sandbox01Session [value01=" + value01 + ", value02=" + value02 + "]";
	}

}
