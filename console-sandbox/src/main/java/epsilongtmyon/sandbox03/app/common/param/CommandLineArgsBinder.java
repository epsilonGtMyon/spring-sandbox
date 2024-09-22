package epsilongtmyon.sandbox03.app.common.param;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.ApplicationArguments;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

/**
 * コマンドライン引数をオブジェクトへバインドするクラス。
 */
@Component
public class CommandLineArgsBinder {

	private final ApplicationArguments args;

	private final ConversionService conversionService;

	public CommandLineArgsBinder(ApplicationArguments args, Optional<ConversionService> conversionService) {
		this.args = args;
		this.conversionService = conversionService.orElseGet(DefaultConversionService::getSharedInstance);
	}

	/**
	 * コマンドライン引数をオブジェクトとして取得します。
	 * @param <T>
	 * @param clazz オブジェクトのクラス。
	 * @return バインド済みのオブジェクト
	 */
	public <T> T bindAndGetAs(Class<T> clazz) {
		try {
			T instance = clazz.getDeclaredConstructor().newInstance();
			bind(instance);
			return instance;
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * コマンドライン引数をオブジェクトにバインドします。
	 * @param <T>
	 * @param instance 対象のオブジェクト
	 */
	public <T> void bind(T instance) {
		MutablePropertyValues propValues = argsToPropertyValues(args);

		DataBinder binder = new DataBinder(instance);
		binder.setConversionService(conversionService);
		binder.bind(propValues);
	}

	private MutablePropertyValues argsToPropertyValues(ApplicationArguments args) {
		MutablePropertyValues propValues = new MutablePropertyValues();

		args.getOptionNames().forEach(name -> {
			List<String> values = args.getOptionValues(name);
			propValues.addPropertyValue(name, values);
		});

		return propValues;
	}

}
