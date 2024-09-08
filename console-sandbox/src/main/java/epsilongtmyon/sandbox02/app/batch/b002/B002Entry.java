package epsilongtmyon.sandbox02.app.batch.b002;

import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.Batch;

@Batch("B002")
@Component
public class B002Entry {

	public void execute() {

		System.out.println("B002Entry");
	}
}
