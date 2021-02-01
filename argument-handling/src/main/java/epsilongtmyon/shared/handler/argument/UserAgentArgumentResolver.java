package epsilongtmyon.shared.handler.argument;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import epsilongtmyon.shared.UserAgent;

/**
 * リクエストヘッダからUser-Agentを取得する引数ハンドラ
 */
public class UserAgentArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return UserAgent.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		String userAgent = webRequest.getHeader("User-Agent");
		if (userAgent == null || userAgent.isEmpty()) {
			return null;
		}
		return new UserAgent(userAgent);
	}

}
