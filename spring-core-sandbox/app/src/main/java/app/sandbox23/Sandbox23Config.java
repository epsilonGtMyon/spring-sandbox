package app.sandbox23;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("classpath:/app/sandbox23/app.properties")
@PropertySource("classpath:/app/sandbox23/${propName:app}.properties")
public class Sandbox23Config {

}
