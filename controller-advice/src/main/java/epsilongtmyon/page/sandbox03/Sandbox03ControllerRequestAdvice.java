package epsilongtmyon.page.sandbox03;

import java.io.IOException;
import java.lang.reflect.Type;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

/*
 * RequestMappingHandlerAdapterで持っている
 *
 * RequestResponseBodyAdviceChainでワンクッションはさんで
 * AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters で呼ばれる
 * なのでハンドラーメソッドの引数が
 *   ・HttpEntityMethodProcessor
 *   ・RequestResponseBodyMethodProcessor
 * あたりのもので処理されるときに呼ばれる
 * (RequestPartMethodArgumentResolverはよくわからん..)
 *
 *
 *
 */
@ControllerAdvice(basePackageClasses = Sandbox03Controller.class)
public class Sandbox03ControllerRequestAdvice implements RequestBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		Thread.dumpStack();
		return Sandbox03Hoge.class.isAssignableFrom(methodParameter.getParameterType());
	}

	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		System.out.println("beforeBodyRead");
		// TODO 自動生成されたメソッド・スタブ
		return inputMessage;
	}

	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		System.out.println("afterBodyRead");
		// TODO 自動生成されたメソッド・スタブ
		var hoge = (Sandbox03Hoge) body;
		hoge.doHoge();
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		System.out.println("handleEmptyBody");
		// TODO 自動生成されたメソッド・スタブ
		return body;
	}

}
