package epsilongtmyon.app.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import epsilongtmyon.app.common.servlet.filter.Order01Filter;
import epsilongtmyon.app.common.servlet.filter.Order02Filter;
import epsilongtmyon.app.common.servlet.filter.Order03Filter;

// SpringBootだとFilterを@Beanや@ComponentでBean登録しておくことで ServletContextInitializerBeansで自動的にFilterとして適用される
// こっちの方法で直接登録することもできる。

@Configuration
public class FilterConfiguration {

	// Filterの中になにかInjectionしたい場合はBeanのメソッドの引数で受け取ってコンストラクタに渡す
	@Bean
	FilterRegistrationBean<Order01Filter> order01Filter() {
		FilterRegistrationBean<Order01Filter> fb = new FilterRegistrationBean<>(new Order01Filter());
		// こんな感じにパターンや順序を後付けで設定できる。
		// 
		// そうじゃない場合はFilter に @Order のアノテーションを付けるか、Orderedをimplements する
		// Spring Bootだと CharacterEncodingFilterに対してOrderedCharacterEncodingFilterというクラスを作っておりそれをBean登録するというやり方もやっている。
		fb.addUrlPatterns("/sandbox01/*");
		fb.setOrder(500);
		return fb;
	}

	@Bean
	FilterRegistrationBean<Order02Filter> order02Filter() {
		FilterRegistrationBean<Order02Filter> fb = new FilterRegistrationBean<>(new Order02Filter());
		fb.setOrder(501);
		return fb;
	}

	@Bean
	FilterRegistrationBean<Order03Filter> order03Filter() {
		FilterRegistrationBean<Order03Filter> fb = new FilterRegistrationBean<>(new Order03Filter());
		fb.setOrder(499);
		return fb;
	}
}
