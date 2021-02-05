package epsilongtmyon.page.sandbox01;

import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import epsilongtmyon.shared.formatter.FlagFormatter;

/*
 * 1.3.7. Controller Advice
 * ControllerAdviceBean 経由で使われる？
 *   使われているところ
 *   ExceptionHandlerExceptionResolver
 *     @ExceptionHandler を検出
 *   RequestMappingHandlerAdapter
 *     @InitBinder
 *     @ModelAttribute
 *     を検出
 *
 *   RequestResponseBodyAdviceChain
 *     RequestBodyAdviceインターフェース
 *     ResponseBodyAdviceインターフェース
 *     を実装しているものを検出
 *
 *     AbstractMessageConverterMethodArgumentResolverで使われているので
 *     HttpMessageConverter関連と一緒に使われるのかな
 *
 */
@ControllerAdvice(basePackageClasses = Sandbox01Controller.class)
public class Sandbox01ControllerAdvice {

	/*
	 * ServletInvocableHandlerMethod#invokeAndHandle()
	 *   親のInvocableHandlerMethod#invokeForRequest()
	 *       InvocableHandlerMethod#getMethodArgumentValues
	 *         からのHandlerMethodArgumentResolverComposite#resolveArgument()
	 *
	 *         つまりは引数解決のタイミングで実行される
	 *
	 *         今回のケースだと
	 *           ModelAttributeMethodProcessor#resolveArgument()
	 *           の中でresolveArgumentの引数であるbinderFactory
	 *             InitBinderDataBinderFactory#initBinder()
	 *             が呼ばれてここで@InitBinderのメソッドが呼ばれる
	 *
	 *         binderFactoryはどこで作られる？
	 *           RequestMappingHandlerAdapterクラスでServletInvocableHandlerMethodのインスタンスを作った後
	 *           getDataBinderFactoryメソッドで作ったものがセットされる
	 *             ここでは@ControllerAdviceで条件に適したもの(isApplicableToBeanTypeで判断)から@InitBinderがついたメソッド
	 *             あとはController自体についた@InitBinderがついたメソッド
	 *             が選択される
	 *             んで ServletRequestDataBinderFactoryが返される
	 *
	 *
	 *           RequestMappingHandlerAdapter#getDefaultInitBinderArgumentResolvers()
	 *           で作ってる引数は渡せるみたい
	 *
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addCustomFormatter(new FlagFormatter());
	}

	/*
	 * RequestMappingHandlerAdapter#invokeHandlerMethod()の
     *   ModelFactory#initModel
     *     ModelFactory#invokeModelAttributeMethods
     *       ここで@ModelAttributeのメソッドを実行して
     *       ModelAndViewContainerにセットしている。
     *
     *       対象メソッドの集め方は@InitBinderみたいな感じ
	 */
	@ModelAttribute("bloodTypes")
	public List<String> bloodTypes() {
		Thread.dumpStack();
		return List.of("A", "B", "AB", "O");
	}
}