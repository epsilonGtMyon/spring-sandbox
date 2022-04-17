package app.sandbox19;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class Sandbox19Config {

	@Bean
	public Sandbox19Hoge sandbox18Hoge(Sandbox19Foo sandbox18Foo) {
		return new Sandbox19Hoge(sandbox18Foo);
	}

	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public Sandbox19Foo sandbox18Foo() {
		return new Sandbox19Foo();
	}

}
