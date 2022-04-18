package app.section02.sandbox0203;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = Sandbox0203Config.class)
@PropertySource({"classpath:/app/section02/sandbox0203/application.properties"})
public class Sandbox0203Config {

}
