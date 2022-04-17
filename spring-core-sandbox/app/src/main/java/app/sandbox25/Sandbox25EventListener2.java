package app.sandbox25;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Sandbox25EventListener2 implements ApplicationListener<Sandbox25Event> {

	@Override
	public void onApplicationEvent(Sandbox25Event event) {
		System.out.println("------------------");
		System.out.println(getClass());
		System.out.println(event);
		System.out.println(event.getSource());
	}

}
