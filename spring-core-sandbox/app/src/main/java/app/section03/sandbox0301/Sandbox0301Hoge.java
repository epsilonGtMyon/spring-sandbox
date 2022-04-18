package app.section03.sandbox0301;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Component;

@Component
public class Sandbox0301Hoge {

	private final ConversionService conversionService;

	public Sandbox0301Hoge(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public void hoge() {
		BigDecimal b = conversionService.convert("500", BigDecimal.class);
		System.out.println(b);

		List<Integer> input = List.of(1, 2, 3);

		Object strList = conversionService.convert(input,
				TypeDescriptor.forObject(input), // List<Integer> type descriptor
				TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(String.class)));
		System.out.println((List<String>)strList);

	}

}
