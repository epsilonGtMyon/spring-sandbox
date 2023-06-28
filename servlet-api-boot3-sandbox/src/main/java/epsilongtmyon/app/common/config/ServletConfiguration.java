package epsilongtmyon.app.common.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import epsilongtmyon.app.common.servlet.servlet.My01Servlet;

// DispatcherServletの登録は DispatcherServletRegistrationBeanでされていてこれは DispatcherServletAutoConfiguration でされている
// spring.mvc.servletのプロパティで設定できる(pathの変更とか)

@Configuration
public class ServletConfiguration {

	// Servletを追加したい場合はこんな感じ
	@Bean
	ServletRegistrationBean<My01Servlet> my01Servlet() {

		ServletRegistrationBean<My01Servlet> registration = new ServletRegistrationBean<>(new My01Servlet());
		registration.addUrlMappings("/my01/*");
		registration.setLoadOnStartup(2);

		return registration;
	}
}
