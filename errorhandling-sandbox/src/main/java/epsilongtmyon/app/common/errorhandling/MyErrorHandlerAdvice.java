package epsilongtmyon.app.common.errorhandling;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import epsilongtmyon.app.common.exception.My02Exception;

@RestControllerAdvice
public class MyErrorHandlerAdvice {


	// DispatcherServlet#processHandlerExceptionから呼ばれる
	// ExceptionHandlerExceptionResolver#getExceptionHandlerMethod 参考
	@ExceptionHandler({ My02Exception.class })
	public Map<String, Object> errorHandling(Exception e) {
		return Map.of(
				"handler", "MyErrorHandlerAdvice",
				
				"message", "例外です",

				"exceptionClass", e.getClass().getName(),

				"exceptionMessage", e.getMessage());
	}
}
