package epsilongtmyon.page.sandbox03;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackageClasses = Sandbox03Controller.class)
public class Sandbox03ControllerResponseAdvice implements ResponseBodyAdvice<Sandbox03Response01> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		System.out.println("supports2");
		Thread.dumpStack();
		return Sandbox03Response01.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public Sandbox03Response01 beforeBodyWrite(Sandbox03Response01 body, MethodParameter returnType,
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		System.out.println("beforeBodyWrite");
		Thread.dumpStack();
		// TODO 自動生成されたメソッド・スタブ

		body.setValue01(body.getValue01() + ":advice");
		return body;
	}

}
