package epsilongtmyon.app.common.errorhandling;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// warのときはErrorPageFilter,jarでTomcatの時はStandardHostValveで折り返されてここに到達する。
// おりかえされる都合上ServletフィルターやHandlerInterceptorを通過してくるので
// そこで妨害されないように注意が必要
@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class MyErrorController extends AbstractErrorController {

	private final ErrorProperties errorProperties;

	public MyErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
		super(errorAttributes, Collections.emptyList());
		this.errorProperties = serverProperties.getError();
	}

	@RequestMapping
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		HttpStatus status = getStatus(request);
		Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.of(Include.values()));
		return new ResponseEntity<>(body, status);
	}
}
