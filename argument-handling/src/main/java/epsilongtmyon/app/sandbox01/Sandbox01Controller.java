package epsilongtmyon.app.sandbox01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import epsilongtmyon.app.sandbox01.apispec.Get02Request;
import epsilongtmyon.app.sandbox01.apispec.Post01Request;
import epsilongtmyon.app.sandbox01.apispec.Post02Request;

/*
 * ServletInvocableHandlerMethod
 *  親のInvocableHandlerMethodのgetMethodArgumentValues
 *    親のHandlerMethodのgetMethodParametersで MethodParameter[] を取得
 *      これがハンドラーメソッドの各パラメータのメタ情報みたいなもの
 *
 *    そのあとはMethodParameterそれぞれに対して
 *    HandlerMethodArgumentResolverのresolveArgumentを実行して値をとりだす
 *
 *  doInvokeメソッドでハンドラーのメソッドに引数を渡して実行
 *  って感じだった
 *
 *
 * HandlerMethodArgumentResolverについて
 *  supportsParameterメソッドで使用するものが選択されている。
 *
 *
 *
 *
 */

@Controller
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	private static final String VIEW_NAME = "sandbox01/index";

	@GetMapping("")
	public String index() {
		return VIEW_NAME;
	}

	@GetMapping("get01")
	public String get01(
			//RequestParamMethodArgumentResolver
			@RequestParam("val01") String val01,

			//RedirectAttributesMethodArgumentResolver
			RedirectAttributes rd,

			//ModelMethodProcessor
			Model model) {

		return VIEW_NAME;
	}

	@GetMapping("get02")
	public String get02(
			/*
			 * ServletModelAttributeMethodProcessorが使われる
			 *   親クラスのModelAttributeMethodProcessor
			 *     bindRequestParameters メソッドで
			 *      ServletRequestDataBinder#bind でリクエストパラメータをバインド
			 *     validateIfApplicable でバリデーションもしているみたい
			 *     次の引数がBindingResultかどうかの判断もしている。
			 *
			 * binderについてはどっかでまた調べたい
			 */
			Get02Request request) {
		System.out.println(request);
		return VIEW_NAME;
	}

	@PostMapping("post01")
	public String post01(
			//ServletModelAttributeMethodProcessorが使われる
			Post01Request request) {
		System.out.println(request);
		return VIEW_NAME;
	}

	@PostMapping("post02")
	@ResponseBody
	public String post02(
			/*
			 * RequestResponseBodyMethodProcessorが使われる
			 *   親のAbstractMessageConverterMethodProcessorでvalidationなど
			 *
			 */
			@RequestBody Post02Request request) {
		System.out.println(request);
		return "aaaa";
	}
}
