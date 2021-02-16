package epsilongtmyon.page.sandbox01;

import epsilongtmyon.shared.messageconverter.OneLineCsvAsignable;

public class Sandbox01CsvRequest implements OneLineCsvAsignable {

	private String value01;
	private String value02;
	private String value03;
	private String value04;

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

	public String getValue04() {
		return value04;
	}

	public void setValue04(String value04) {
		this.value04 = value04;
	}

	@Override
	public String toString() {
		return "Sandbox01CsvRequest [value01=" + value01 + ", value02=" + value02 + ", value03=" + value03
				+ ", value04=" + value04 + "]";
	}

	@Override
	public void asignValue(int index, String value) {
		switch (index) {
		case 0:
			value01 = value;
			break;
		case 1:
			value02 = value;
			break;
		case 2:
			value03 = value;
			break;
		case 3:
			value04 = value;
			break;
		}

	}

}
