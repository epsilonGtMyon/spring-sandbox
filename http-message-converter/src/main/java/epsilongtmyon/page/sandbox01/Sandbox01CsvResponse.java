package epsilongtmyon.page.sandbox01;

import java.util.List;

import epsilongtmyon.shared.messageconverter.OneLineCsv;

public class Sandbox01CsvResponse implements OneLineCsv {

	private String value01;
	private String value02;
	private String value03;

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

	public String getValue03() {
		return value03;
	}

	public void setValue03(String value03) {
		this.value03 = value03;
	}

	@Override
	public String toString() {
		return "Sandbox01CsvResponse [value01=" + value01 + ", value02=" + value02 + ", value03=" + value03 + "]";
	}

	@Override
	public List<String> getValues() {
		return List.of(value01, value02, value03);
	}

}
