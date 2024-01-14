package epsilongtmyon.app.common.spring.factories;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;


// デフォルトだと application.propertiesの読み込みにはPropertiesPropertySourceLoaderが使われて
// OriginTrackedPropertiesLoaderが使われる
// 内部クラスのCharacterReaderにてISO_8859_1が直接指定されているためUTF-8だと読み込むことができない。

// このクラスを/META-INF/spring.properties に追加することで
// StandardConfigDataLocationResolverで読み込まれるようになる
// そのあとStandardConfigDataReferenceに渡されるのでStandardConfigDataLoaderでこのクラスが使われるようになる。

/**
 * UTF-8 のapplication.propertiesを読み込むためのPropertySourceLoader
 * @author epsil
 *
 */
public class Utf8PropertySourceLoader implements PropertySourceLoader {
	

	// PropertySourceProcessor参考
	private static final PropertySourceFactory defaultPropertySourceFactory = new DefaultPropertySourceFactory();

	@Override
	public String[] getFileExtensions() {
		return new String[] { "properties" };
	}

	@Override
	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
		System.out.println("======");
		System.out.println(name);
		System.out.println(resource);
		
		PropertySource<?> propertySource = defaultPropertySourceFactory.createPropertySource(name,
				new EncodedResource(resource, StandardCharsets.UTF_8));

		List<PropertySource<?>> list = new ArrayList<>();
		list.add(propertySource);

		return list;
	}

}
