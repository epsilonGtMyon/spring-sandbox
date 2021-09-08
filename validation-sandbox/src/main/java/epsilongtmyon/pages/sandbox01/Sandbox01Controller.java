package epsilongtmyon.pages.sandbox01;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import epsilongtmyon.pages.sandbox01.spec.Sandbox01Request;

@RequestMapping("sandbox01")
@Controller
public class Sandbox01Controller {

	private Logger logger = LoggerFactory.getLogger(Sandbox01Controller.class);

	@GetMapping({ "", "index" })
	public String index() {
		logger.info("index");
		return "sandbox01/index";
	}

	//TODO Validationがどこで 作成されているか
	//カスタマイズするにはどうしたら良いか

	/*
	 * ModelAttributeMethodProcessor でパラメータのバインドが実行される
	 * そこでorg.springframework.validation.DataBinderのvalidateメソッドを呼ぶ
	 *
	 * 何もカスタマイズしていない状態だとValidatorAdapter が使われる
	 * 中のtargetは LocalValidatorFactoryBean が使われているようだ
	 * んでさらに org.hibernate.validator.internal.engine.ValidatorImpl に委譲してる感じ
	 *
	 */
	@GetMapping("executeGet")
	@ResponseBody
	public String executeGet(@Validated(Xxxx.class) Sandbox01Request request) {
		logger.info("executeGet");
		logger.info(Objects.toString(request, null));
		return "1";
	}

	/*
	 * RequestResponseBodyMethodProcessor でパラメータのバインドが実行される
	 * 後の流れは上と同じかな
	 */
	@PostMapping("executeAjaxPost")
	@ResponseBody
	public String executeAjaxPost(@RequestBody @Validated Sandbox01Request request) {
		logger.info("executeAjaxPost");
		logger.info(Objects.toString(request, null));
		return "1";
	}

	public interface Xxxx {

	}
}
