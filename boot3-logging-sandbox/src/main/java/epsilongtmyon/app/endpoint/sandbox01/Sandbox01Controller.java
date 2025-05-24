package epsilongtmyon.app.endpoint.sandbox01;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Sandbox01Controller.class);

	@GetMapping("/get")
	public Map<String, String> get() {
		logger.debug("hello debug: message");
		logger.info("hello info: {}", LocalDateTime.now().toString());
		return Map.of("value", LocalDateTime.now().toString());
	}
}
