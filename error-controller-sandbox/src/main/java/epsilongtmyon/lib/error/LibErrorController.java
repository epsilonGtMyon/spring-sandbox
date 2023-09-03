package epsilongtmyon.lib.error;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import epsilongtmyon.lib.ControllerDateHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * コードメモ
 * 
 * ErrorMvcAutoConfigurationにて
 * ErrorAttributes の実装クラス DefaultErrorAttributesがBean定義されている。
 * 
 * DefaultErrorAttributesはHandlerExceptionResolverを実装しており、例外が発生したときは
 * resolveExceptionが実行され、ここで例外のインスタンスをリクエストに退避している。
 * 
 * その後の処理で/errorへ転送する処理が行われErrorControllerが実行される。
 * ここではErrorPropertiesから例外の情報を取り出すことで適切なレスポンスを返す処理を行う。
 */

@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class LibErrorController extends AbstractErrorController {

	private final ErrorProperties errorProperties;

	private final ErrorAttributes errorAttributes;

	private final ControllerDateHolder controllerDateHolder;

	public LibErrorController(
			ErrorAttributes errorAttributes,
			ErrorProperties errorProperties,
			ControllerDateHolder controllerDateHolder) {
		this(errorAttributes, errorProperties, Collections.emptyList(), controllerDateHolder);
	}

	public LibErrorController(
			ErrorAttributes errorAttributes,
			ErrorProperties errorProperties,
			List<ErrorViewResolver> errorViewResolvers,
			ControllerDateHolder controllerDateHolder) {
		super(errorAttributes, errorViewResolvers);
		this.errorAttributes = errorAttributes;
		this.errorProperties = errorProperties;
		this.controllerDateHolder = controllerDateHolder;
	}

	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request, HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		if (status == HttpStatus.NO_CONTENT) {
			return new ResponseEntity<>(status);
		}

		// 例外から取り出したいものを定義
		ErrorAttributeOptions options = ErrorAttributeOptions.defaults()
				.including(Include.EXCEPTION, Include.STACK_TRACE, Include.MESSAGE, Include.BINDING_ERRORS);

		// それをもとに取り出す(以下のものが含まれているそうだ)
		// optionsで含めるものを設定できる。
		// status: HttpStatusコード
		// error: ステータスコードに応じたメッセージ
		// exception: 発生した例外のgetSimpleName
		// trace: スタックトレース
		// message: リクエストにあればそれ、なければ例外のメッセージ
		// path: 例外が発生したリクエストのパス
		Map<String, Object> body = getErrorAttributes(request, options);

		// スタックトレースを取り出す
		// String stackTrace = (String) body.get("trace");

		// 発生した例外そのものを取り出す
		// Throwable th = getError(request);

		// リクエストスコープの内容もとれる
		System.out.println("controllerDate");
		System.out.println(controllerDateHolder.getControllerDate());

		return new ResponseEntity<>(body, status);
	}

	private Throwable getError(HttpServletRequest request) {
		WebRequest webRequest = new ServletWebRequest(request);
		return errorAttributes.getError(webRequest);
	}

}
