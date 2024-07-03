package epsilongtmyon.app.endpoint.api2.sandboxA;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sandboxA")
public class SandboxAController {

	@GetMapping("/hello")
	public Map<String, Object> hello() {
		var response = new HashMap<String, Object>();

		response.put("value", "Hello");

		return response;
	}
}
