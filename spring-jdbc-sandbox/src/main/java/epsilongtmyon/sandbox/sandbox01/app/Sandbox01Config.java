package epsilongtmyon.sandbox.sandbox01.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import epsilongtmyon.common.config.SpringJdbcConfig;

@Configuration
@ComponentScan
@Import(SpringJdbcConfig.class)
public class Sandbox01Config {

}
