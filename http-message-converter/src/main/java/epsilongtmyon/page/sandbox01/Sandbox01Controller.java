package epsilongtmyon.page.sandbox01;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	@GetMapping("get01")
	public Map<String, Object> get01() {
		return Map.of("aaa", 123);
	}
}
