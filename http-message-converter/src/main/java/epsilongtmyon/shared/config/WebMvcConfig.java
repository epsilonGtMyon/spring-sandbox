package epsilongtmyon.shared.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import epsilongtmyon.shared.messageconverter.OneLineCsvMessageConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/sandbox01").setViewName("sandbox01/index");
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		//優先順位を上げるために先頭に割り込む
		converters.add(0, new OneLineCsvMessageConverter());
	}

}
