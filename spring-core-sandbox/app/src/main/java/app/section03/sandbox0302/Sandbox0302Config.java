package app.section03.sandbox0302;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

@Configuration
@ComponentScan(basePackageClasses = Sandbox0302Config.class)
public class Sandbox0302Config {

	@Bean
	public FormattingConversionServiceFactoryBean formattingConversionService() {
		return new FormattingConversionServiceFactoryBean();
	}
}
