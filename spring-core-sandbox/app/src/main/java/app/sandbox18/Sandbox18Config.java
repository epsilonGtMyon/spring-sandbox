package app.sandbox18;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Sandbox18Config {

	//デフォルトではclose, shatdownがdestroyメソッド
	//JNDIから取得するようなアプリケーション外で管理するようなものは自動で廃棄されるとこまるので
	//明示的に空文字列にしておくことがいいらしい、これはその例(newしてますが..)
	@Bean(destroyMethod="")
	public Sandbox18Hoge sandbox18Hoge(Sandbox18Foo sandbox18Foo) {
		return new Sandbox18Hoge(sandbox18Foo);
	}

	@Bean
	public Sandbox18Foo sandbox18Foo() {
		return new Sandbox18Foo();
	}

}
