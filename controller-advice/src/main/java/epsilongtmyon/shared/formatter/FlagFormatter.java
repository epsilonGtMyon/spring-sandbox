package epsilongtmyon.shared.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import epsilongtmyon.shared.type.Flag;

public class FlagFormatter implements Formatter<Flag> {

	@Override
	public String print(Flag object, Locale locale) {
		return object == null ? null : object.value;
	}

	@Override
	public Flag parse(String text, Locale locale) throws ParseException {
		return Flag.of(text);
	}

}
