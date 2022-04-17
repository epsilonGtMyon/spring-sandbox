package app.sandbox15;

import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Sandbox15Configuration {

	@Bean
	@Scope("prototype")
	public Sandbox15Bean sandbox15Bean(InjectionPoint injectionPoint) {
		//どこにインジェクションされようとしているかとれる
		return new Sandbox15Bean(injectionPoint.getMember().toString());
	}
}
