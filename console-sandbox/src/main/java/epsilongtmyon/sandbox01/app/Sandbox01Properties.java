package epsilongtmyon.sandbox01.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

/*
 * ConfigurationPropertiesの値は
 * --sandbox01.value01=xxx のようなコマンドライン引数でも設定することができる。
 */

@ConfigurationProperties(prefix = "sandbox01")
public class Sandbox01Properties {

	private String value01;

	private Integer intValue01;

	public String getValue01() {
		return value01;
	}

	public void setValue01(String value01) {
		this.value01 = value01;
	}

	public Integer getIntValue01() {
		return intValue01;
	}

	public void setIntValue01(Integer intValue01) {
		this.intValue01 = intValue01;
	}

	@Override
	public String toString() {
		return "Sandbox01Properties [value01=" + value01 + ", intValue01=" + intValue01 + "]";
	}

}
