package epsilongtmyon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

@SpringBootApplication
public class WebLocaleSandboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebLocaleSandboxApplication.class, args);
	}

	// LocaleResolverはデフォルトだとAcceptHeaderLocaleResolverが定義されている。
	// フロントエンド側から切り替えるようにするにはCookieLocaleResolver, SessionLocaleResolverなどがある
	// セッションに保存したら、ログアウトなどしてセッションが消えるともとに戻ってしまうのでCookieにする。
	// setDefaultLocaleFunctionで見つからなかった場合のフォールバックの処理が書ける
	// この時にヘッダーから解決するようにする。
	//
	// 実際のアプリだとCookieやSessionを確認して、存在しなければその時点で明示的に初期値をいれるようにしたほうがいいかも、

	@Bean(name = DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
	public LocaleResolver localeResolver(WebProperties webProperties) {
		final AcceptHeaderLocaleResolver headerLocaleResolver = new AcceptHeaderLocaleResolver();
		headerLocaleResolver.setDefaultLocale(webProperties.getLocale());

		// --------------

		// Cookie名はデフォルトだとSpringを使っていることが露骨にバレる内容なのでコンストラクタで書き換える。
		final CookieLocaleResolver localeResolver = new CookieLocaleResolver("appLocale");
		localeResolver.setDefaultLocaleFunction(headerLocaleResolver::resolveLocale);
		return localeResolver;
	}

	// LocaleContextHolder は OrderedRequestContextFilter(RequestContextFilter)でrequest.getLocaleの値で初期状態の設定が行われ
	// その後DispatcherServletのinitContextHoldersでリクエストの処理が行われる際にLocaleResolverの結果が設定され
	// リクエストの処理が終わるときに処理前の状態に戻している。
	// なのでDispatcherServletの外側だとLocaleResolverから取得した値の参照ができなさそう..
}
