package app.sandbox31;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;

@Configuration
@ComponentScan(basePackageClasses = Sandbox31Config.class)
public class Sandbox31Config {

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		return new ConversionServiceFactoryBean();
	}
}
