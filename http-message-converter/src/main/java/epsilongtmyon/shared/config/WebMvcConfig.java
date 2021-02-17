package epsilongtmyon.shared.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.shared.messageconverter.OneLineCsvMessageConverter;

/*
 * ↓これはbootのクラス
 * EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport
 *   に含まれている WebMvcConfigurerComposite から呼び出されて登録が行われる
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/sandbox01").setViewName("sandbox01/index");
	}

	/*
	 * WebMvcConfigurationSupport のgetMessageConvertersから呼び出される
	 *
	 * jackson入ってたらそれ用のHttpMessageConverterを追加するのも
	 * WebMvcConfigurationSupportで行われている
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		//優先順位を上げるために先頭に割り込む
		converters.add(0, new OneLineCsvMessageConverter());
	}

}
