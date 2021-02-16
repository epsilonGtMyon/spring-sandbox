package epsilongtmyon.shared.messageconverter;

import java.util.Collections;
import java.util.List;

public interface OneLineCsv {

	default void asignValue(int index, String value) {
	}

	default List<String> getValues() {
		return Collections.emptyList();
	}
}
