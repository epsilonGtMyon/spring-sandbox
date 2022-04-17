package app.sandbox32;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configuration
@ComponentScan(basePackageClasses = Sandbox32Config.class)
public class Sandbox32Config {

	@Bean
	public FormattingConversionServiceFactoryBean formattingConversionService() {
		return new FormattingConversionServiceFactoryBean();
	}
}
