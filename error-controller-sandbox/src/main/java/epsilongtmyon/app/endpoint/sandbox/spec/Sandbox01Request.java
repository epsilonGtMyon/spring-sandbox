package epsilongtmyon.app.endpoint.sandbox.spec;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Sandbox01Request {

	/** code01 */
	@NotEmpty
	@Size(max = 5)
	private String code01;

	public String getCode01() {
		return code01;
	}

	public void setCode01(String code01) {
		this.code01 = code01;
	}

}
