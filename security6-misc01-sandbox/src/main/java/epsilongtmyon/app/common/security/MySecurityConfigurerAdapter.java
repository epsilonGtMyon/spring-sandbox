package epsilongtmyon.app.common.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.OncePerRequestFilter;

/*
 * CsrfConfigurer などを参考
 * 
 * AbstractHttpConfigurer を継承したものをつくることで
 * HttpSecurityにいろいろ介入できるものを作ることができる。
 */
public class MySecurityConfigurerAdapter extends AbstractHttpConfigurer<MySecurityConfigurerAdapter, HttpSecurity> {

	// フィールドとかセッターをつけて、SecurityConfig側で設定してもらって
	// configureでそれらをもとにHttpSecurityにいろいろする(フィルター追加したり)のがよさそう
	private String hoge;

	@Override
	public void init(HttpSecurity builder) throws Exception {
		// Thread.dumpStack();
	}

	@Override
	public void configure(HttpSecurity builder) throws Exception {
		// Thread.dumpStack();
		System.out.println(hoge);

		// builder.getSharedObject(XXX.class) などでSpring Securityが内部で利用するものを取得することができる。

		MyFilter filter = new MyFilter();
		filter = postProcess(filter);
		builder.addFilterAt(filter, CsrfFilter.class);
	}

	@Override
	public void setBuilder(HttpSecurity builder) {
		// Thread.dumpStack();
		super.setBuilder(builder);
	}

	public void setHoge(String hoge) {
		this.hoge = hoge;
	}

	
	public static class MyFilter extends OncePerRequestFilter {
		
		// このクラス自体はBean登録されないが
		// このフィールドはAutowireBeanFactoryObjectPostProcessorにより注入してくれる。
		@Autowired
		private Environment env;

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
				FilterChain filterChain) throws ServletException, IOException {
			System.out.println(env);
			
			filterChain.doFilter(request, response);
		}
		
	}
}
