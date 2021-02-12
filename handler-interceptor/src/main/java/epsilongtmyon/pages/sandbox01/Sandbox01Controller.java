package epsilongtmyon.pages.sandbox01;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	private static final String VIEW_NAME = "sandbox01/index";

	@GetMapping
	public String index() {
		return VIEW_NAME;
	}

	@GetMapping("get01")
	public String get01() {
		return VIEW_NAME;
	}

	@GetMapping("get02")
	@ResponseBody
	public Map<String, Object> get02() {
		return Map.of("key", "aaaa");
	}
}
