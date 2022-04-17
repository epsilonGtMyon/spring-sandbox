package app.sandbox27;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import app.sandbox27.Sandbox27Event.S27EventSource1;
import app.sandbox27.Sandbox27Event.S27EventSource2;

public class Sandbox27Main {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				Sandbox27Config.class)) {
			context.publishEvent(new Sandbox27Event<S27EventSource1>(new S27EventSource1()));
			context.publishEvent(new Sandbox27Event<S27EventSource2>(new S27EventSource2()));
		}
	}
}
