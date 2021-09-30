package epsilongtmyon.app.shared.config.security.authentication;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

//FormLoginConfigurer を参考
public class JsonBodyLoginConfigurer<H extends HttpSecurityBuilder<H>> extends
		AbstractAuthenticationFilterConfigurer<H, JsonBodyLoginConfigurer<H>, JsonBodyUsernamePasswordAuthenticationFilter> {

	public JsonBodyLoginConfigurer() {
		super(new JsonBodyUsernamePasswordAuthenticationFilter(), null);
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, "POST");
	}

}
