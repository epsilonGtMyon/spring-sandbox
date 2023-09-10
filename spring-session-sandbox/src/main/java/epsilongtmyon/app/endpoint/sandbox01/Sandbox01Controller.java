package epsilongtmyon.app.endpoint.sandbox01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import epsilongtmyon.app.endpoint.sandbox01.spec.Sandbox01Response;

@RequestMapping("/sandbox01")
@RestController
public class Sandbox01Controller {

	private final Sandbox01Session session;

	public Sandbox01Controller(Sandbox01Session session) {
		super();
		this.session = session;
	}

	@GetMapping("/hello1")
	public String hello1() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		session.setValue01(now);
		return "hello";
	}

	@GetMapping("/hello2")
	public String hello2() {
		String now = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
		session.setValue02(now);
		return "hello";
	}

	@GetMapping("/get")
	public Sandbox01Response get() {
		return Sandbox01Response.fromSession(session);
	}

}
