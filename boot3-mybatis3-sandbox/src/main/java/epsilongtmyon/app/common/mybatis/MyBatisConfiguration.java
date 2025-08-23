package epsilongtmyon.app.common.mybatis;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import epsilongtmyon.app.common.mybatis.plugin.CommonFieldInterceptor;

// MyBatisのカスタマイズやいろいろ追加とか
// MybatisAutoConfiguration を見るといい
// プロパティならMybatisProperties
// カスタマイザならSqlSessionFactoryBeanCustomizer, ConfigurationCustomizer など

@Configuration
public class MyBatisConfiguration {

	@Bean
	ConfigurationCustomizer myConfigurationCustomizer() {
		return (configuration) -> {
			// xmlで設定してたようなことはここでできる。

			configuration.setMapUnderscoreToCamelCase(true);

			configuration.addInterceptor(new CommonFieldInterceptor());

		};
	}

	@Bean
	SqlSessionFactoryBeanCustomizer mySqlSessionFactoryBeanCustomizer(ResourceLoader resourceLoader) {
		return (sqlSessionFactoryBean) -> {
			
			// もしくは設定ファイルでmybatis.mapperLocations で指定する。
			// 一つ一つやらんといかんのかな
			Resource mapper = resourceLoader.getResource("classpath:epsilongtmyon/app/sandbox/sandbox02/mapper/Sandbox02Mapper-rename.xml");
			sqlSessionFactoryBean.addMapperLocations(mapper);

		};
	}
}
