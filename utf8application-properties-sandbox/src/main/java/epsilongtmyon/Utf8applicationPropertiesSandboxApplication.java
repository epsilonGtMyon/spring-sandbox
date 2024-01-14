package epsilongtmyon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigurationProperties({ AppProperties.class })
//PropertySourceはエンコードが指定できるし読み込まれるところもapplication.propertiesとは別
@PropertySource(value = "classpath:/my.properties", encoding = "UTF-8")
public class Utf8applicationPropertiesSandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(Utf8applicationPropertiesSandboxApplication.class, args);
	}

}
