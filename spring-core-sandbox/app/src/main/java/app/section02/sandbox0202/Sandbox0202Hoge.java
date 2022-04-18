package app.section02.sandbox0202;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

public class Sandbox0202Hoge implements ResourceLoaderAware {

	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		//実体はapplicationContextが渡されるっぽい
		this.resourceLoader = resourceLoader;
	}

}
