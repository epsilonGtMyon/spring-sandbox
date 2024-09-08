package epsilongtmyon.sandbox02.app.batch.b001;

import org.springframework.stereotype.Component;

import epsilongtmyon.sandbox02.app.common.exec.Batch;

@Batch("B001")
@Component
public class B001Entry {

	public void execute() {

		System.out.println("B001Entry");
		
		throw new RuntimeException("yahoo");
	}
}
