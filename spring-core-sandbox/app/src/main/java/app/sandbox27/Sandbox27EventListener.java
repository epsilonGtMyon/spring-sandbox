package app.sandbox27;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import app.sandbox27.Sandbox27Event.S27EventSource1;
import app.sandbox27.Sandbox27Event.S27EventSource2;

@Component
public class Sandbox27EventListener {

	@EventListener
	public void handle1(Sandbox27Event<S27EventSource1> event) {
		System.out.println("handle1: " + getClass());
		System.out.println(event);
		System.out.println(event.getSource());

	}

	@EventListener
	public void handle2(Sandbox27Event<S27EventSource2> event) {
		System.out.println("handle2: " + getClass());
		System.out.println(event);
		System.out.println(event.getSource());
	}
}
