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
 *   jacksonが使える場合だと
 *   JsonViewRequestBodyAdviceが入ってるようだ
 *
 * RequestResponseBodyAdviceChainでワンクッションはさんで
 * AbstractMessageConverterMethodArgumentResolver#readWithMessageConverters で呼ばれる
 * なのでハンドラーメソッドの引数がこれのサブクラスである
 *   ・HttpEntityMethodProcessor
 *   ・RequestResponseBodyMethodProcessor
 * あたりのもので処理されるときに呼ばれる
 * (RequestPartMethodArgumentResolverはよくわからん..)
 *
 * readWithMessageConvertersで使われるが
 *   HttpMessageConverter の処理対象が見つかった時にAdviceのメソッドも呼ばれるので
 *   そもそもHttpMessageConverter が見つからないと何も呼ばれない
 *
 * RequestResponseBodyAdviceChainで複数のものが集約された状態で管理されている
 *
 * あと適用対象のフィルタリングは
 * RequestResponseBodyAdviceChain#getMatchingAdviceの中で
 * ControllerAdviceBean#isApplicableToBeanTypeが呼ばれることで行われている
 *
 */
@ControllerAdvice(basePackageClasses = Sandbox03Controller.class)
public class Sandbox03ControllerRequestAdvice implements RequestBodyAdvice {

	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		return Sandbox03Hoge.class.isAssignableFrom(methodParameter.getParameterType());
	}

	/*
	 * HttpMessageConverterでのread前に呼ばれる
	 * HttpInputMessage をラップしたり？？
	 *
	 * JsonViewRequestBodyAdviceの使い方を見ると
	 *   MappingJacksonInputMessageを返していて
	 *   AbstractJackson2HttpMessageConverterの中でinstanceofでそれ使って
	 *   っていう使い方してるね
	 */
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return inputMessage;
	}

	/*
	 * HttpMessageConverterでのread後に呼ばれる
	 * 具体的なインスタンスが出来上がってるので なんかできそう
	 */
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
			Class<? extends HttpMessageConverter<?>> converterType) {
		var hoge = (Sandbox03Hoge) body;
		hoge.doHoge();//追加で処理呼んでみたり
		return body;//ラップして返したりできそう
	}

	/*
	 * bodyが空の時に呼ばれる
	 * ここに来るときはreadは呼ばれていない
	 */
	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
			Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		//bodyはnullだが
		//自分より前のRequestBodyAdviceが何か返したときはそれが渡ってくる
		return body;
	}

}
