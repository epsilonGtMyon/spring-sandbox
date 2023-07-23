package epsilongtmyon.app.common.validation;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class ValueValidator {

	private static final Pattern halfNumPattern = Pattern.compile("^[0-9]+$");
	private static final Pattern halfAlphaNumPattern = Pattern.compile("^[a-zA-Z0-9]+$");

	private static boolean isEmptyText(String value) {
		return value == null || value.isEmpty();
	}

	/**
	 * 必須チェックを行います。
	 * 
	 * @param value 値
	 * @return チェックを通過していればtrue
	 */
	public static boolean validateRequired(Object value) {
		if (value == null) {
			return false;
		}

		if (value instanceof CharSequence c) {
			return !c.isEmpty();
		}

		if (value instanceof Collection<?> c) {
			return !c.isEmpty();
		}

		if (value instanceof Map<?, ?> m) {
			return !m.isEmpty();
		}

		return true;
	}

	/**
	 * 半角数値チェックを行います。
	 * 
	 * @param value 値
	 * @return チェックを通過していればtrue
	 */
	public static boolean validateHalfNum(String value) {
		if (isEmptyText(value)) {
			return true;
		}

		return halfNumPattern.matcher(value).matches();
	}

	/**
	 * 半角英数チェックを行います。
	 * 
	 * @param value 値
	 * @return チェックを通過していればtrue
	 */
	public static boolean validateHalfAlphaNum(String value) {
		if (isEmptyText(value)) {
			return true;
		}

		return halfAlphaNumPattern.matcher(value).matches();
	}
}
