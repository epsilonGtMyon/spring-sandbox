package epsilongtmyon.app.endpoint.sandbox01;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.common.exception.My01Exception;
import epsilongtmyon.app.common.exception.My02Exception;
import epsilongtmyon.app.common.exception.My03Exception;
import epsilongtmyon.app.common.exception.My04Exception;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {
	
	// それぞれの例外をスローし
	// それぞれ異なる箇所でハンドリングする。
	
	// ControllerAdviceでのハンドリングも実質ExceptionHandlerExceptionResolverというHandlerExceptionResolverの実装クラスでハンドリングが行われるので
	// ハンドリング箇所としてほぼ同じところで行われる
	// DispatcherServletで行われるためFilterなどで発生した場合はハンドリングされない
	//
	// 一方でErrorControllerは一度折り返してハンドリングが行われるのでFilterでもハンドリングが行われる
	// ただし折り返しにおいてもFilterやHandlerInterceptorを通過するためそこで妨害されないように注意が必要
	

	/**
	 * ControllerのExceptionHandlerでハンドリング
	 * @return
	 */
	@GetMapping("my01Exception")
	public String my01Exception() {
		throw new My01Exception("例外");
	}

	/**
	 * ControllerAdviceのExceptionHandlerでハンドリング
	 * @return
	 */
	@GetMapping("my02Exception")
	public String my02Exception() {
		throw new My02Exception("例外");
	}

	/**
	 * HandlerExceptionResolverでハンドリング
	 * @return
	 */
	@GetMapping("my03Exception")
	public String my03Exception() {
		throw new My03Exception("例外");
	}

	/**
	 * ErrorControllerでハンドリング
	 * @return
	 */
	@GetMapping("my04Exception")
	public String my04Exception() {
		throw new My04Exception("例外");
	}


	// DispatcherServlet#processHandlerExceptionから呼ばれる
	// ExceptionHandlerExceptionResolver#getExceptionHandlerMethod 参考
	@ExceptionHandler({ My01Exception.class })
	public Map<String, Object> errorHandling(Exception e) {
		return Map.of(
				"handler", "Sandbox01Controller",
				
				"message", "例外です",

				"exceptionClass", e.getClass().getName(),

				"exceptionMessage", e.getMessage());
	}
}
