package epsilongtmyon.returnvaluesandbox.app.sandbox02;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UncheckedIOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
 * DefaultErrorAttributes
 *   リクエストスコープにエラーの情報を詰め込んでいる
 * HandlerExceptionResolverComposite
 *   ExceptionHandlerExceptionResolver
 *     getExceptionHandlerMethod
 *       Controllerクラス拾ってきて
 *       ExceptionHandlerMethodResolver#resolveMethodで対象メソッドを取得
 *         このクラスをnewしたときに対象のクラス(この場合はController)のメソッドをフィルタリングして
 *         @ExceptionHandlerがついているメソッドを取得している。
 *       それをServletInvocableHandlerMethodに詰め込んで返す
 *       実行したメソッドはHandlerMethodReturnValueHandlerで処理される
 *
 *       見つからなかったときはControllerAdviceがついてるものを探してきて
 *       メソッド探してる感じ
 *
 *   ResponseStatusExceptionResolver
 *     まだ見てない
 *   DefaultHandlerExceptionResolver
 *     いろんな例外のハンドリングがされている
 */
@Controller
@RequestMapping("sandbox02")
public class Sandbox02Controller {

	@ExceptionHandler({ Sandbox02Exception.class })
	public ModelAndView Aaa(Sandbox02Exception ex) {
		Thread.dumpStack();

		try (StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw)) {
			ex.printStackTrace(pw);
			pw.flush();

			ModelAndView mav = new ModelAndView("sandbox02/index");
			mav.addObject("ex", sw.toString());

			return mav;

		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	@GetMapping("get01")
	public String get01() {
		throw new Sandbox02Exception();
	}

	@GetMapping("get02")
	public String get02() {
		throw new Sandbox0202Exception();
	}

	public static class Sandbox02Exception extends RuntimeException {

	}


	public static class Sandbox0202Exception extends RuntimeException {

	}
}
