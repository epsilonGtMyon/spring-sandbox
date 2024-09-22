package epsilongtmyon.sandbox03.app;

import java.util.ArrayList;
import java.util.List;

public class Sandbox03Param {

	private String value01;

	private Integer intValue01;

	private List<String> listValue01;

	private NestedParam nested01;

	private List<NestedParam> nestedList = new ArrayList<>();

	// ------------------------------------

	@Override
	public String toString() {
		return "Sandbox03Param [value01=" + value01 + ", intValue01=" + intValue01 + ", listValue01=" + listValue01
				+ ", nested01=" + nested01 + ", nestedList=" + nestedList + "]";
	}

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

	public List<String> getListValue01() {
		return listValue01;
	}

	public void setListValue01(List<String> listValue01) {
		this.listValue01 = listValue01;
	}

	public NestedParam getNested01() {
		return nested01;
	}

	public void setNested01(NestedParam nested01) {
		this.nested01 = nested01;
	}

	public List<NestedParam> getNestedList() {
		return nestedList;
	}

	public void setNestedList(List<NestedParam> nestedList) {
		this.nestedList = nestedList;
	}

	public static class NestedParam {
		private String nestValue01;
		private Integer nestIntValue01;
		private boolean nestBoolValue01;

		public String getNestValue01() {
			return nestValue01;
		}

		public void setNestValue01(String nestValue01) {
			this.nestValue01 = nestValue01;
		}

		public Integer getNestIntValue01() {
			return nestIntValue01;
		}

		public void setNestIntValue01(Integer nestIntValue01) {
			this.nestIntValue01 = nestIntValue01;
		}

		public boolean isNestBoolValue01() {
			return nestBoolValue01;
		}

		public void setNestBoolValue01(boolean nestBoolValue01) {
			this.nestBoolValue01 = nestBoolValue01;
		}

		@Override
		public String toString() {
			return "NestedParam [nestValue01=" + nestValue01 + ", nestIntValue01=" + nestIntValue01
					+ ", nestBoolValue01=" + nestBoolValue01 + "]";
		}

	}
}
