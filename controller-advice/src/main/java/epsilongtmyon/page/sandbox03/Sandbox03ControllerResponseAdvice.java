package epsilongtmyon.page.sandbox03;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/*
 * RequestMappingHandlerAdapter, ExceptionHandlerExceptionResolverで持っている
 * 通常のハンドラーメソッドだけじゃなくて@ExceptionHandlerの時も適用対象
 *
 *   jacksonが使える場合だと
 *   JsonViewResponseBodyAdvice
 *
 * RequestResponseBodyAdviceChainでワンクッションはさんで
 * AbstractMessageConverterMethodProcessor#writeWithMessageConverters で呼ばれる
 * なのでハンドラーメソッドの戻り値がこれのサブクラスである
 * ・HttpEntityMethodProcessor
 * ・RequestResponseBodyMethodProcessor
 * のときに呼ばれる
 *
 * writeWithMessageConvertersで使われるとは書いたものの
 *   HttpMessageConverter の処理対象が見つかった時にAdviceのメソッドも呼ばれるので
 *   そもそもHttpMessageConverter が見つからないと何も呼ばれない
 *
 *   あとメディアタイプも絡んでそう
 *
 *
 * RequestResponseBodyAdviceChainで複数のものが集約された状態で管理されている
 *
 */
@ControllerAdvice(basePackageClasses = Sandbox03Controller.class)
public class Sandbox03ControllerResponseAdvice implements ResponseBodyAdvice<Sandbox03Response01> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return Sandbox03Response01.class.isAssignableFrom(returnType.getParameterType());
	}

	/*
	 * HttpMessageConverterでのwrite前に呼ばれる
	 * 加工したり
	 * なんか呼び出したりかな？
	 *
	 * JsonViewResponseBodyAdviceを見ると
	 * 追加で何かセットするという使い方をしている
	 * そしてそれがHttpMessageConverterで使われてる
	 */
	@Override
	public Sandbox03Response01 beforeBodyWrite(Sandbox03Response01 body, MethodParameter returnType,
			MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		body.setValue01(body.getValue01() + ":advice");
		return body;
	}

}
