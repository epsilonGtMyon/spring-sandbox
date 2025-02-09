package epsilongtmyon.app.endpoint.emp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.endpoint.emp.api.spec.EmpRegisterRequest;
import epsilongtmyon.app.endpoint.emp.api.spec.EmpSearchResponse;
import epsilongtmyon.app.endpoint.emp.bl.EmpService;
import epsilongtmyon.app.endpoint.emp.bl.io.EmpRegisterParam;
import epsilongtmyon.app.endpoint.emp.bl.io.EmpSearchResult;

/*
 * DBにアクセスするだけのクラス
 */
@RequestMapping("/emp")
@RestController
public class EmpController {

	private final EmpService service;

	public EmpController(EmpService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("emp/index");
	}

	/**
	 * 全件取得
	 * 
	 * @return
	 */
	@GetMapping("/search")
	public EmpSearchResponse search() {

		EmpSearchResult result = service.search();

		EmpSearchResponse resp = EmpSearchResponse.fromResult(result);
		return resp;
	}

	/**
	 * 登録
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Validated EmpRegisterRequest request) {

		final EmpRegisterParam param = request.toParam();
		service.register(param);
		return ResponseEntity.ok().build();
	}

}
