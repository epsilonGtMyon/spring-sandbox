package app.sandbox33.bean;

import javax.validation.constraints.NotEmpty;

import app.sandbox33.validator.Sandbox33Annotation;

public class Sandbox33Hoge {

	@NotEmpty
	private String value01;

	@Sandbox33Annotation
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

}
