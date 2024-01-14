package epsilongtmyon.app.endpoint.sandbox01;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.AppProperties;

@RestController
@RequestMapping("/sandbox01")
public class Sandbox01Controller {

	/** ロガー */
	private static final Logger log = LoggerFactory.getLogger(Sandbox01Controller.class);

	@Autowired
	private AppProperties appProps;

	@GetMapping("/get")
	public Map<String, String> get() {
		log.info("");
		log.info(appProps.getHoge01());
		log.info(appProps.getHoge02());
		log.info(appProps.getFoo01());

		return Map.of(
				"hoge01", appProps.getHoge01(),

				"hoge02", appProps.getHoge02(),

				"foo01", appProps.getFoo01()

		);
	}
}
