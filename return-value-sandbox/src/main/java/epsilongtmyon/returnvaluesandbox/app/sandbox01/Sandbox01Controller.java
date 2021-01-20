package epsilongtmyon.returnvaluesandbox.app.sandbox01;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/*
 * どのHandlerMethodReturnValueHandlerが呼ばれるか確認したメモ
 *
 * ServletInvocableHandlerMethodクラスのフィールドにreturnValueHandlers(HandlerMethodReturnValueHandlerCompositeクラス)を持っている
 * これがinvokeAndHandle時に参照されて適したHandlerMethodReturnValueHandler が選択される
 *
 * HandlerMethodReturnValueHandlerのsupportsReturnTypeに応じて適切なものが使用されるようだ
 *
 * これは
 * RequestMappingHandlerAdapterのafterPropertiesSetでセットされる？？
 *
 */

@Controller
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	/*
	 * ViewNameMethodReturnValueHandlerが使われる
	 */
	@GetMapping("get01")
	public String get01(Model model) {
		model.addAttribute("val", "get01だよ");
		return "sandbox01/index";
	}

	/*
	 * ModelAndViewMethodReturnValueHandlerが使われる
	 *
	 */
	@GetMapping("get02")
	public ModelAndView get02() {
		final ModelAndView mav = new ModelAndView("sandbox01/index");
		mav.addObject("val", "get01だよ");
		return mav;
	}


	/*
	 * HttpEntityMethodProcessorが使われる
	 *
	 * 親のAbstractMessageConverterMethodProcessorクラスの
	 * writeWithMessageConvertersが使われる
	 */
	@GetMapping("get03")
	public ResponseEntity<String> get03(){
		return ResponseEntity.ok("ResponseEntityだよ");
	}

	/*
	 * RequestResponseBodyMethodProcessorが使われる
	 *
	 * 親のAbstractMessageConverterMethodProcessorクラスの
	 * writeWithMessageConvertersが使われる
	 */
	@GetMapping("get04")
	@ResponseBody
	public String get04() {
		return "@ResponseBodyだよ";
	}

}
