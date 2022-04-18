package app.section03.sandbox0302;

import org.springframework.format.support.FormattingConversionService;
import org.springframework.stereotype.Component;

@Component
public class Sandbox0302Hoge {

	private final FormattingConversionService formattingConversionService;

	public Sandbox0302Hoge(FormattingConversionService formattingConversionService) {
		this.formattingConversionService = formattingConversionService;
	}

	public void hoge() {

	}

}
