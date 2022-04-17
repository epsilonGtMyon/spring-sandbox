package app.sandbox32;

import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;

@Component
public class Sandbox32Hoge {

	private final FormattingConversionService formattingConversionService;

	public Sandbox32Hoge(FormattingConversionService formattingConversionService) {
		this.formattingConversionService = formattingConversionService;
	}

	public void hoge() {

	}

}
