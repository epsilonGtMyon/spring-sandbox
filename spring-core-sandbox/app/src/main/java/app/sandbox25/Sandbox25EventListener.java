package app.sandbox25;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

//ApplicationListenerDetectorで検知・登録される
@Component
public class Sandbox25EventListener implements ApplicationListener<Sandbox25Event> {

	@Override
	public void onApplicationEvent(Sandbox25Event event) {
		System.out.println(getClass());
		System.out.println(event.getSource());
	}

}
