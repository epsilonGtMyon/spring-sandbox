package epsilongtmyon.app.endpoint.counter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import epsilongtmyon.app.endpoint.counter.spec.CounterGetResponse;

/*
 * SessionScopeのBeanを使って検証
 * 
 * SessionScopeのBeanは、アクセスしたときに
 * ServletRequestAttributes#getAttribute経由でセッションから取得される。
 * この時にsessionAttributesToUpdateフィールドにsetAttributeの対象として記録され
 * ServletRequestAttributes#requestCompletedが呼ばれたタイミングでsetAttributeがまとめて実行される。
 */
@RequestMapping("/counter")
@RestController
public class CounterController {

	private final CounterBean counterBean;

	public CounterController(CounterBean counterBean) {
		super();
		this.counterBean = counterBean;
	}

	@GetMapping
	public ModelAndView index() {
		return new ModelAndView("counter/index");
	}

	@GetMapping("/countUp")
	public CounterGetResponse countUp() {

		counterBean.access();

		CounterGetResponse resp = new CounterGetResponse();
		resp.setCount(counterBean.getCount());
		resp.setAccessTimes(counterBean.getAccessTimes());
		return resp;
	}
}
