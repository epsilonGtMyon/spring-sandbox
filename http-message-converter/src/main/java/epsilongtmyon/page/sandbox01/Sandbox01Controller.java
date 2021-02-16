package epsilongtmyon.page.sandbox01;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * AbstractMessageConverterMethodArgumentResolver あたりで使われてるので
 * ・HttpEntityMethodProcessor
 * ・RequestResponseBodyMethodProcessor
 * あたりで使われる
 * 他にもあるが..
 *
 */
@RestController
@RequestMapping("sandbox01")
public class Sandbox01Controller {

	@GetMapping("get01")
	public Map<String, Object> get01(
			//ServletModelAttributeMethodProcessorが使われるので関係ない
			Sandbox01Request request) {
		return Map.of("aaa", 123);
	}

	@PostMapping("post01")
	public Map<String, Object> post01(
			//MappingJackson2HttpMessageConverterが使われる
			@RequestBody Sandbox01Request request) {
		return Map.of("aaa", 123);
	}

	@PostMapping("postCsv")
	public Map<String, Object> postCsv(
			@RequestBody Sandbox01CsvRequest request) {
		System.out.println(request);
		return Map.of("aaa", request.toString());
	}

	//TODO WebMvcConfigurationSupportあたり←これはboot?
}
