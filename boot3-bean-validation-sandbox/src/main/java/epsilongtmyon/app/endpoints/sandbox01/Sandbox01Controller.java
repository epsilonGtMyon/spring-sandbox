package epsilongtmyon.app.endpoints.sandbox01;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import epsilongtmyon.app.endpoints.sandbox01.spec.Sandbox01RegisterRequest;
import epsilongtmyon.app.endpoints.sandbox01.spec.Sandbox01RegisterResponse;

@Controller
@RequestMapping("/sandbox01")
public class Sandbox01Controller {
	
	private final MessageSource messageSource;
	

	public Sandbox01Controller(MessageSource messageSource) {
		super();
		this.messageSource = messageSource;
	}

	@GetMapping(value = { "", "/" })
	public String index() {
		return "sandbox01/index";
	}

	@PostMapping("/register")
	@ResponseBody
	public Sandbox01RegisterResponse register(
			@RequestBody @Validated Sandbox01RegisterRequest request) {
		

		return new Sandbox01RegisterResponse();
	}
	
	
	@ExceptionHandler(value = {
			MethodArgumentNotValidException.class
	})
	public ResponseEntity<Object> handleError(Exception ex) {
		if(ex instanceof MethodArgumentNotValidException nex) {
			BindingResult bindingResult = nex.getBindingResult();
			
			// ObjectErrorがDefaultMessageSourceResolvableなのでMessageSourceに渡すとプレースホルダーの置換までしてくれる
			// BeanValidationのエラーメッセージ関連は SpringValidatorAdapter#processConstraintViolationsでvalidationを行った後のことをしている
			// メッセージ解決にどのキー(コード)を使うかは MessageCodesResolverで導出している
			List<ObjectError> errors = bindingResult.getAllErrors();
			List<String> messages = errors.stream().map(e -> messageSource.getMessage(e, null)).toList();
			return ResponseEntity.ok(messages);
		}
		return null;
		
	}

}
