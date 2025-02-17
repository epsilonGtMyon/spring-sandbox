package epsilongtmyon.app.endpoint.sandbox01;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.lib.txtoken.annotation.TxToken;
import epsilongtmyon.lib.txtoken.annotation.TxToken.TxTokenAction;

@RequestMapping("/sandbox01")
@RestController
public class Sandbox01Controller {

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("sandbox01/index");
	}

	@PostMapping("/publishTxToken")
	@TxToken(TxTokenAction.PUBLISH)
	public ResponseEntity<?> publishTxToken() {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/noAction")
	public ResponseEntity<?> noAction() {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/checkTxToken")
	@TxToken(TxTokenAction.CHECK)
	public ResponseEntity<?> checkTxToken() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}

	// -----------------------------------

	@PostMapping("/throwException1")
	public ResponseEntity<?> throwException1() {
		throw new RuntimeException("例外");
	}

	@PostMapping("/throwException2")
	@TxToken(TxTokenAction.PUBLISH)
	public ResponseEntity<?> throwException2() {
		throw new RuntimeException("例外");
	}

	@PostMapping("/throwException3")
	@TxToken(TxTokenAction.CHECK)
	public ResponseEntity<?> throwException3() {
		throw new RuntimeException("例外");
	}

}
