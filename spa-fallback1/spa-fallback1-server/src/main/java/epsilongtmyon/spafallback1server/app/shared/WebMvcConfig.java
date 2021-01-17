package epsilongtmyon.spafallback1server.app.shared;

import java.io.IOException;
import java.time.Duration;

import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	private final Resources resourceProperties;

	public WebMvcConfig(Resources resourceProperties) {
		super();
		this.resourceProperties = resourceProperties;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//設定する内容は
		//WebMvcAutoConfiguration$EnableWebMvcConfiguration.addResourceHandlersを参考にする。

		//ResourceHandlerRegistry#getHandlerMapping()メソッドで
		//SimpleUrlHandlerMappingを返しているが
		//これの作成に使っているのはStringをキーとするMapなので
		//「/**」を指定することで
		////WebMvcAutoConfiguration$EnableWebMvcConfiguration.addResourceHandlersで先に設定されたものを上書きできる
		registry.addResourceHandler("/**")
				.addResourceLocations(this.resourceProperties.getStaticLocations())
				.setCachePeriod(getSeconds(this.resourceProperties.getCache().getPeriod()))
				.setCacheControl(this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl())
				.resourceChain(true)
				.addResolver(new SpaPathResourceResolver());
	}

	private Integer getSeconds(Duration cachePeriod) {
		return (cachePeriod != null) ? (int) cachePeriod.getSeconds() : null;
	}

	static class SpaPathResourceResolver extends PathResourceResolver {

		protected Resource getResource(String resourcePath, Resource location) throws IOException {
			Resource resource = super.getResource(resourcePath, location);
			if (resource != null) {
				return resource;
			}
			return super.getResource("index.html", location);
		};
	}

}
