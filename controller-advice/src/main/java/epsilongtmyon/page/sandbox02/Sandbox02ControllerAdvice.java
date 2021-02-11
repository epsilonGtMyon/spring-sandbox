package epsilongtmyon.page.sandbox02;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * 例外ハンドリング
 * コントローラーのメソッドで例外が投げられると
 * DispatcherServletのところでcatchされて
 *   processDispatchResult
 *   からのprocessHandlerExceptionって流れ
 *
 * DefaultErrorAttributes#resolveExceptionでリクエスト属性にエラー情報の付与
 * HandlerExceptionResolverComposite#resolveException
 *   ExceptionHandlerExceptionResolver
 *     ここで@Exceptionハンドラーは処理される！
 *
 *     親のAbstractHandlerMethodExceptionResolver#doResolveException
 *     getExceptionHandlerMethodでハンドリングのメソッドを解決
 *       beanに付与してるものから探して
 *       なければControllerAdviceから
 *
 *     メソッドの実行結果はHandlerMethodReturnValueHandlerで処理されるので
 *     遷移先を渡したり、json返したりできる
 *
 *   ResponseStatusExceptionResolver
 *     Adviceとは関係ないが
 *     ResponseStatusExceptionだったり
 *     @ResponseStatusが付与されたExceptionのハンドリング
 *
 *   DefaultHandlerExceptionResolver
 *     あらかじめ用意されたいくつかの例外のハンドリング
 *     ModelAndViewを返すようにしている
 *
 *
 */
@ControllerAdvice(basePackageClasses = Sandbox02Controller.class)
public class Sandbox02ControllerAdvice {

	@ExceptionHandler({ Sandbox02AException.class })
	public String handle(Sandbox02AException ex, Model model) {
		try (StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw)) {
			ex.printStackTrace(pw);
			pw.flush();

			model.addAttribute("ex", sw.toString());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return "sandbox02/index";
	}


	@ExceptionHandler({ Sandbox02BException.class })
	@ResponseBody
	public String handle2(Sandbox02BException ex) {
		try (StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw)) {
			ex.printStackTrace(pw);
			pw.flush();

			return sw.toString();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
