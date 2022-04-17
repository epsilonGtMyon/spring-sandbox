package app.sandbox30;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = Sandbox30Config.class)
@PropertySource({"classpath:/app/sandbox30/application.properties"})
public class Sandbox30Config {

}
