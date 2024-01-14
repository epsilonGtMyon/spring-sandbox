package epsilongtmyon;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "eps.app")
public class AppProperties {

	private String hoge01;

	private String hoge02;

	private String foo01;

	public String getHoge01() {
		return hoge01;
	}

	public void setHoge01(String hoge01) {
		this.hoge01 = hoge01;
	}

	public String getHoge02() {
		return hoge02;
	}

	public void setHoge02(String hoge02) {
		this.hoge02 = hoge02;
	}

	public String getFoo01() {
		return foo01;
	}

	public void setFoo01(String foo01) {
		this.foo01 = foo01;
	}

}
